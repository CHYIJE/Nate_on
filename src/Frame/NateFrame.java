package Frame;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NateFrame extends JFrame {
	
	public JLabel background;
	public JButton startButton;
	public JButton stopButton;
	public JLabel port;
	public Font f;

	
	public  NateFrame() {
		initData();
		setInitLayout();
		
	}
	
	private void initData() {
		background = new JLabel(new ImageIcon("img/nateBackground.png"));
		startButton = new JButton("연결");
		stopButton = new JButton("종료");
		port = new JLabel("포트번호 : ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(background);
		setSize(400, 775);
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
		startButton.setLocation(60, 650);
		
		add(stopButton);
		stopButton.setVisible(true);
		stopButton.setSize(100, 50);
		stopButton.setLocation(230, 650);
		
		add(port);
		port.setVisible(true);
		port.setSize(100, 30);
		port.setLocation(60, 600);
		port.setFont(f);
		
	}
	
	
	
	public static void main(String[] args) {
		 new NateFrame();
	}

}
