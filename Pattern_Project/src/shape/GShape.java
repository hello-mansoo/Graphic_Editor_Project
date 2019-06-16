package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import shape.GAnchors.EAnchors;

public abstract class GShape implements Cloneable, Serializable {
	public enum EOnState {
		eOnShape, eOnResize, eOnRotate
	};

	// attributes
	private static final long serialVersionUID = 1L;
	protected int px;
	protected int py;

	// components
	protected Shape shape;
	private GAnchors anchors;
	private EAnchors anchor;
	private Color color;
	private int stroke;

	private boolean selected;

	public Shape getShape() {
		return this.shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}

	public EAnchors getAnchor() {
		return this.anchor;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if (this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
		}
	}

	public GShape() {
		this.selected = false;
		this.anchors = new GAnchors();
	}

	abstract public void setOrigin(int x, int y);

	abstract public void setPoint(int x, int y);

	abstract public void addPoint(int x, int y);

	public void initMoving(Graphics2D graphics2d, int x, int y) {
		this.px = x;
		this.py = y;
		if (this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}

	public abstract void keepMoving(Graphics2D graphics2d, int x, int y);

	public abstract void finishMoving(Graphics2D graphics2d, int x, int y);

	public GShape clone() {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			return (GShape) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public abstract GShape newInstance();

	public void draw(Graphics2D graphics2d) {
		Color temp = graphics2d.getColor();
		if (this.color!=null) {
			graphics2d.setColor(color);
			graphics2d.fill(this.shape);
			graphics2d.setColor(temp);
		}
		
		if (this.stroke!=0) {
			graphics2d.setStroke(new BasicStroke(this.stroke));
		}
		
		graphics2d.draw(this.shape);
		graphics2d.setStroke(new BasicStroke(1));
		
		if (this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}

	public void verticalPaste() {
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(10, 10);
		this.shape = affineTransform.createTransformedShape(this.shape);
	}

	public EOnState onShape(int x, int y) {
		if (this.selected) {
			EAnchors eAnchor = this.anchors.onShape(x, y);
			
			if (eAnchor == EAnchors.RR) {
				return EOnState.eOnRotate;
			} else if (eAnchor == null) {
				if (this.shape.contains(x, y)) {
					return EOnState.eOnShape;
				}
			} else {
				this.anchor = eAnchor;
				return EOnState.eOnResize;
			}
		} else {
			if (this.shape.contains(x, y)) {
				return EOnState.eOnShape;
			}
		}
		return null;
	}

}
