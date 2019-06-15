package transformer;

import java.awt.Graphics2D;

import shape.GShape;

public abstract class GTransformer {

	private GShape gShape;
	public GTransformer() {
		this.setgShape(null);
	}
	public GShape getgShape() { return gShape; }
	public void setgShape(GShape gShape) { this.gShape = gShape; }
	
	abstract public void initTransforming(Graphics2D graphics2d, int x, int y);
	abstract public void keepTransforming(Graphics2D graphics2d, int x, int y);
	abstract public void finishTransforming(Graphics2D graphics2d, int x, int y);

}
