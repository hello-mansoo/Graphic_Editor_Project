package transformer;

import java.awt.Graphics2D;

public class GDrawer extends GTransformer {
	
	public GDrawer() {
		
	}

	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.getgShape().setOrigin(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		this.getgShape().draw(graphics2d);
		this.getgShape().setPoint(x, y);
		this.getgShape().draw(graphics2d);
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {

	}

}
