package main;

public class GMain {
	static private GMainFrame mainFrame;
	public static void main(String[] args) {
		mainFrame = new GMainFrame();
		mainFrame.initialize(); // 메모리 할당이 완료 후 다시 하는 작업.
		mainFrame.setVisible(true);
	}

}
