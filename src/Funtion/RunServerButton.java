package Funtion;

import javax.swing.JButton;

public class RunServerButton extends JButton {
	
	private JButton startButton;
	
	public RunServerButton() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setSize(100, 200);
	}
	
	private void setInitLayout() {
		setVisible(false);
	}
	
	private void addEventListener() {
		// 이벤트 등록 
//		startButton.addActionListener();
	}
	
	

}
  