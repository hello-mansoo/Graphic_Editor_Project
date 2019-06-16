package shape;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GLine extends GShape {
	private static final long serialVersionUID = 1L;
	
	private java.awt.geom.Line2D line;

	public GLine() {
		super();
		this.shape = new java.awt.geom.Line2D.Double(0,0,0,0);
		this.line = (java.awt.geom.Line2D)this.shape;
	}
	
	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		this.line.setLine(x,y,x,y);
	}

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		this.line.setLine(this.line.getX1(),this.line.getY1(),x,y);
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keepMoving(Graphics2D graphics2d, int x, int y) {
		// TODO Auto-generated method stub
		int dw = x-px;
		int dh = y-py;
		
		AffineTransform af = new AffineTransform();
		af.translate(dw, dh);
		this.shape = af.createTransformedShape(this.shape);
		
		this.px = x;
		this.py = y;
	}

	@Override
	public void finishMoving(Graphics2D graphics2d, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GShape newInstance() {
		// TODO Auto-generated method stub
		return new GLine();
	}

}
