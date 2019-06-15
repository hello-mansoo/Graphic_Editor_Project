package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GMain {
	static private GMainFrame mainFrame;
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainFrame = new GMainFrame();
		mainFrame.initialize(); // 메모리 할당이 완료 후 다시 하는 작업.
		mainFrame.setVisible(true);
	}

}
