package shape;

import java.awt.Graphics2D;

public class GPolygon extends GShape {
	private static final long serialVersionUID = 1L;
	
	private java.awt.Polygon polygon;
	
	public GPolygon() {
		super();
		
		this.polygon = new java.awt.Polygon();
		this.shape = this.polygon; // 점이 몇개인지를 확인하기 위해 폴리곤으로 캐스팅했다.
	}
	
	public GShape newInstance() {
		return new GPolygon();
	}
	
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	public void setPoint(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

	public void keepMoving(Graphics2D graphics2d, int x, int y) {
		int dw = x - this.px;
		int dh = y - this.py;
		
		this.polygon.translate(dw, dh);
	
		this.px = x;
		this.py = y;
	}
	public void finishMoving(Graphics2D graphics2d, int x, int y) {
//		java.awt.Polygon newPolygon = new java.awt.Polygon();
//		for(int i=0; i<this.polygon.npoints; i++) {
//			newPolygon.addPoint(this.polygon.xpoints[i],this.polygon.ypoints[i]);
//		}
//		
//		this.polygon = newPolygon;
//		this.shape = this.polygon; 
	}
}
