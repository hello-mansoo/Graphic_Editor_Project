package transformer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import shape.GAnchors.EAnchors;

public class GResizer extends GTransformer {
	private EAnchors anchor;

	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.setOldPoint(x, y);
		this.anchor = this.gShape.getAnchor();
	}

	private double getDX(double x, double width) {
		return (x - this.oldPoint.getX()) / width;
	}

	private double getDY(double y, double height) {
		return (y - this.oldPoint.getY()) / height;
	}

	@Override
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		// TODO Auto-generated method stub
		AffineTransform transform = new AffineTransform();
		Rectangle bound = this.gShape.getShape().getBounds();
		double dx = this.getDX(x, bound.getWidth());
		double dy = this.getDY(y, bound.getHeight());
		switch (this.anchor) {
		case NW:
			transform.setToTranslation(bound.getMinX() + bound.getWidth(), bound.getMinY() + bound.getHeight());
			transform.scale(1-dx, 1-dy);
			transform.translate(-(bound.getMinX() + bound.getWidth()), -(bound.getMinY() + bound.getHeight()));
			break;
		case NN:
			transform.setToTranslation(0, bound.getMinY() + bound.getHeight());
			transform.scale(1, 1-dy);
			transform.translate(0, -(bound.getMinY() + bound.getHeight()));
			break;
		case NE:
			transform.setToTranslation(bound.getMinX(), bound.getMinY() + bound.getHeight());
			transform.scale(1+dx, 1-dy);
			transform.translate(-(bound.getMinX()), -(bound.getMinY() + bound.getHeight()));
			break;
		case WW:
			transform.setToTranslation(bound.getMinX() + bound.getWidth(), 0);
			transform.scale(1-dx, 1);
			transform.translate(-(bound.getMinX() + bound.getWidth()), 0);
			break;
		case EE:
			transform.setToTranslation(bound.getMinX(), 0);
			transform.scale(1+dx, 1);
			transform.translate(-(bound.getMinX()), 0);
			break;
		case SW:
			transform.setToTranslation(bound.getMinX() + bound.getWidth(), bound.getMinY());
			transform.scale(1-dx, 1+dy);
			transform.translate(-(bound.getMinX() + bound.getWidth()), -(bound.getMinY()));
			break;
		case SS:
			transform.setToTranslation(0, bound.getMinY());
			transform.scale(1, 1+dy);
			transform.translate(0, -(bound.getMinY()));
			break;
		case SE:
			transform.setToTranslation(bound.getMinX(), bound.getMinY());
			transform.scale(1+dx, 1+dy);
			transform.translate(-(bound.getMinX()), -(bound.getMinY()));
			break;
		}
		this.gShape.setShape(transform.createTransformedShape(this.gShape.getShape()));
		this.setOldPoint(x, y);
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		// TODO Auto-generated method stub

	}

}
