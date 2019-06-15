package transformer;

import java.awt.Graphics2D;

public class GMover extends GTransformer {

	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.getgShape().initMoving(graphics2d, x, y);

	}

	@Override
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		this.getgShape().keepMoving(graphics2d,x, y);
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		this.getgShape().finishMoving(graphics2d, x, y);

	}

}
