package drawingPanel;

import java.util.Vector;

import shape.GShape;

public class Clipboard {
	private Vector<GShape> shapes;
	
	public void setContents(Vector<GShape> shapes) {
		this.shapes.clear();
		this.shapes.addAll(shapes);
	}
	
	public Vector<GShape> getContents(){
		return (Vector<GShape>) this.shapes.clone();
	}
}
