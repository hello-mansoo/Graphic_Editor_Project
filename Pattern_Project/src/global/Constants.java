package global;

import shape.GRectangle;
import shape.GPolygon;
import shape.GShape;

public class Constants {

	public enum EMainFrame {
		x(200),
		y(100),
		w(400),
		h(600)
		;
		private int value;
		private EMainFrame (int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
	
	public enum EToolBar {
		rectangle("네모", new GRectangle()),
		rectangle1("네모", new GRectangle()),
		polygon("폴리곤", new GPolygon()),
		;
		private String text;
		private GShape shape;
		private EToolBar (String text, GShape shape) {
			this.text = text;
			this.shape = shape;
		}
		
		public String getText() {
			return this.text;
		}
		
		public GShape getShape() {
			return this.shape;
		}
	}
	public enum EMenu {
		filemenu("파일"),
		editmenu("Edit"),
		;
		private String text;
		
		private EMenu (String text) {
			this.text = text;
		}
		
		public String getText() {
			return this.text;
		}
	}
	public enum EFileMenu {
		newItem("새로만들기", "nnew"),
		openItem("열기", "open"),
		saveItem("저장", "save"),
		saveAsItem("다른이름으로", "saveAs"),
		printItem("인쇄하기", "print"),
		closeItem("닫기", "close"),
		;
		private String text;
		private String method;
		
		private EFileMenu (String text, String method) {
			this.text = text;
			this.method = method;
		}
		
		public String getText() {
			return this.text;
		}
		
		public String getMethod() {
			return this.method;
		}
	}
	
	public enum EEditMenu {
		undo("되돌리기", "undo"),
		redo("다시 실행", "redo"),
		cut("잘라내기", "cut"),
		copy("복사하기", "copy"),
		paste("붙혀넣기", "paste"),
		group("그룹", "group"),
		ungroup("그룹 해제", "ungroup"),
		;
		private String text;
		private String method;
		
		private EEditMenu (String text, String method) {
			this.text = text;
			this.method = method;
		}
		
		public String getText() {
			return this.text;
		}
		
		public String getMethod() {
			return this.method;
		}
	}

}
