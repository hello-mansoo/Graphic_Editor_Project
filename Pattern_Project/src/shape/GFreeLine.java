package shape;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class GFreeLine extends GShape{
	private GeneralPath gPath;
	
	public GFreeLine() {
		super();
		this.shape = new GeneralPath();
		this.gPath = (java.awt.geom.GeneralPath)this.shape;
	}

	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		this.gPath.moveTo(x, y);
	}

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		this.gPath.lineTo(x, y);
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
		return new GFreeLine();
	}

}
