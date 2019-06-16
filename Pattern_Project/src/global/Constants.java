package global;

import shape.GShape;
import shape.GRectangle;
import shape.GPolygon;
import shape.GEllipse;
import shape.GLine;
import shape.GGroup;

public class Constants {
	
	public enum EMainFrame {
		x(200),
		y(100),
		w(800),
		h(600),
		;
		
		private int value;
		
		private EMainFrame(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
	
	public enum EToolBar {
		rectangle("네모", new GRectangle()),
		ellipse("원",new GEllipse()),
		line("선",new GLine()),
		polygon("폴리곤", new GPolygon()),
		select("선택", new GGroup()),
		;
		private String text;
		private GShape shape;
		private EToolBar(String text, GShape shape) {
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
		fileMenu("파일"),
		editMenu("Edit"),
		;
		private String text;
		private EMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	public enum EFileMenu {
		newItem("새로 만들기", "nnew"),
		openItem("열기", "open"),
		saveItem("저장", "save"),
		saveAsItem("다른이름으로", "saveAs"),
		printItem("인쇄하기","print"),
		closeItem("닫기", "close"),
		;
		private String text;
		private String method;
		private EFileMenu(String text, String method) {
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
		redo("다시실행", "redo"),
		cut("잘라내기", "cut"),
		copy("복사하기", "copy"),
		paste("붙여넣기","paste"),
		group("모으기", "group"),
		ungroup("나누기", "ungroup"),
		fill("채우기", "fill")
		;
		private String text;
		private String method;
		private EEditMenu(String text, String method) {
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
