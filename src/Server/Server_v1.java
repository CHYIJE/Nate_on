package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Server_v1 {
	// 프레임 받기
	private ServerFrame serverFrame;
	// mainbord출력 
	private JTextArea mainBoard;
	
	// 포트번호 5000 
	private static final int PORT = 5000;
	
	// 벡터로 printwrither 받는다.
	private Vector<PrintWriter> clientWriters = new Vector<>();
	private ServerSocket serverSocket;
	// 쓰레드 기동 상태
	private boolean isRunning = false;
	
	
	public Server_v1() {
		serverFrame = new ServerFrame(this);
		mainBoard = serverFrame.getMainBoard();
		serverFrame.startButton.addActionListener(e -> startServer());
		serverFrame.stopButton.addActionListener(e -> stopServer());
	}

	private void startServer() {
		if (isRunning) {
			JOptionPane.showMessageDialog(null, "서버가 이미 실행 중입니다.", "알림", JOptionPane.WARNING_MESSAGE);
			return;
		}
		isRunning = true;
		new Thread(() -> {
			try {
				serverSocket = new ServerSocket(PORT);
				mainBoard.append("서버 시작\n");
				while (isRunning) {
					Socket clientSocket = serverSocket.accept();
					ClientHandler handler = new ClientHandler(clientSocket, clientWriters);
					handler.start();
				}
			} catch (IOException e) {
				if (isRunning) {
					e.printStackTrace();
					mainBoard.append("서버 오류 발생: " + e.getMessage() + "\n");
				}
			} finally {
				stopServer();
			}
		}).start();
	}

	private void stopServer() {
		if (!isRunning) {
			JOptionPane.showMessageDialog(null, "서버가 실행 중이지 않습니다.", "알림", JOptionPane.WARNING_MESSAGE);
			return;
		}
		isRunning = false;
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
				mainBoard.append("서버 중지\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ClientHandler extends Thread {

		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;
		private Vector<PrintWriter> clientWriters;

		public ClientHandler(Socket socket, Vector<PrintWriter> clientWriters) {
			this.socket = socket;
			this.clientWriters = clientWriters;
			connectIO();
		}

		private void connectIO() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				clientWriters.add(out);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "서버 입출력 장치 에러!", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public void run() {
			try {
				String message;
				while ((message = in.readLine()) != null) {
					System.out.println("Received : " + message);
					broadcastMessage(message);
					mainBoard.append("Received : " + message + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
					System.out.println("........클라이언트 연결 해제........");
					mainBoard.append("** 클라이언트 연결 해제 **\n");
					synchronized (clientWriters) {
						clientWriters.remove(out);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	} // end of ClientHandler

	// 모든 클라이언트에게 메시지 보내기 - 브로드캐스트
	private void broadcastMessage(String message) {
		synchronized (clientWriters) {
			for (PrintWriter writer : clientWriters) {
				writer.println(message);
			}
		}
	}
	

	public static void main(String[] args) {
		new Server_v1();
	}

} // end of class