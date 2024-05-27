package Server;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class ServerFrame extends JFrame {

	public JTextArea getMainBoard() {
		return mainBoard;
	}

	private Server_v1 mContext;

	public JLabel background;

	// 포트 로그인
	public JButton startButton;
	public JButton stopButton;
	private JTextField inputPort;
	public JLabel port;
	private JPanel mainPanel;
	private JTextArea mainBoard;
	public Font f;

	public ServerFrame(Server_v1 mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();

	}

	private void initData() {
		background = new JLabel(new ImageIcon("img/say.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(background);
		setSize(400, 775);

		// 메인패널
		mainPanel = new JPanel();
		mainBoard = new JTextArea();
		mainBoard.setEditable(false);

		// 포트입력
		inputPort = new JTextField(10);
		inputPort.setText("5000");
		startButton = new JButton("연결");
		stopButton = new JButton("종료");
		port = new JLabel("포트번호 : ");
		f = new Font("휴먼편지체", Font.BOLD, 18);

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		add(startButton);
		startButton.setVisible(true);
		startButton.setSize(100, 50);
		startButton.setLocation(60, 640);

		add(stopButton);
		stopButton.setVisible(true);
		stopButton.setSize(100, 50);
		stopButton.setLocation(230, 640);

		add(inputPort);
		inputPort.setVisible(true);
		inputPort.setSize(190, 30);
		inputPort.setLocation(140, 580);

		add(port);
		port.setVisible(true);
		port.setSize(120, 30);
		port.setLocation(60, 580);
		port.setFont(f);

		add(mainPanel);
		mainPanel.setBorder(new TitledBorder(new LineBorder(Color.green, 5), "Server"));
		mainPanel.setBounds(40, 250, 305, 300);
		mainPanel.setBackground(Color.WHITE);

	}
	

}
