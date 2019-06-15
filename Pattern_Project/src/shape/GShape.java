package shape;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import shape.GAnchors.EAnchors;

public abstract class GShape implements Cloneable, Serializable{
	public enum EOnState {eOnShape, eOnResize, eOnRotate};
	//attributes
	private static final long serialVersionUID = 1L;
	protected int px;
	protected int py;
	
	//components
	protected Shape shape;
	private GAnchors anchors;
	
	private boolean selected;
	public Shape getShape() { return this.shape; }
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(this.selected) {
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
		if(this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}
	public abstract void keepMoving(Graphics2D graphics2d, int x, int y);
	public abstract void finishMoving(Graphics2D graphics2d ,int x, int y);
	
	public GShape clone() {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);
			
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			return (GShape)objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public abstract GShape newInstance();
	
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.shape);
		if(this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}
	
	public EOnState onShape(int x, int y) {
		if(this.selected) {
			EAnchors eAnchor = this.anchors.onShape(x, y);
			if(eAnchor == EAnchors.RR) {
				return EOnState.eOnRotate;
			} else if(eAnchor == null) {
				if(this.shape.contains(x,y)) {
					return EOnState.eOnShape;
				}
			} else {
				return EOnState.eOnResize;
			}
		} else {
			if(this.shape.contains(x,y)) {
				return EOnState.eOnShape;
			}
		}
		return null;
	}

}
