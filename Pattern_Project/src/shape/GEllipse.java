package shape;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GEllipse extends GShape {
	private static final long serialVersionUID = 1L;
	
	private java.awt.geom.Ellipse2D ellipse;
	
	public GEllipse() {
		super();
		this.shape = new java.awt.geom.Ellipse2D.Double(0,0,0,0);
		this.ellipse = (java.awt.geom.Ellipse2D)this.shape;
	}
	
	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		this.ellipse.setFrame(x,y,0,0);
	}

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), x - this.ellipse.getX(), y - this.ellipse.getY());
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
		return new GEllipse();
	}
	
}
