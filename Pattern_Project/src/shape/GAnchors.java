package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

public class GAnchors implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final int w = 10;
	private final int h = 10;
	private final int dw = w / 2;
	private final int dh = h / 2;

	public enum EAnchors {
		NW, NN, NE, EE, SE, SS, SW, WW, RR
	}

	private Vector<Ellipse2D> anchors;

	@SuppressWarnings("unused") // 프로그램을 짤 때 실수 한게(안 쓰는 것이) 아니라 일부러 만든 것이란 것을 표시하기 위한것
	public GAnchors() {
		this.anchors = new Vector<Ellipse2D>();
		for (EAnchors eAnchors : EAnchors.values()) {
			this.anchors.add(new Ellipse2D.Double(0, 0, w, h));
		}
	}

	public EAnchors onShape(int x, int y) {
		for(int i = 0; i<EAnchors.values().length; i++) {
			if(this.anchors.get(i).contains(x, y)) {
				return EAnchors.values()[i];
			}
		}
		return null;
	}

	public void draw(Graphics2D graphics2D) {
		for (Shape shape : this.anchors) {
			graphics2D.draw(shape);	
		}
	}

	public void setBoundingRect(Rectangle r) {
		for (EAnchors eAnchors : EAnchors.values()) {
			int x = 0, y = 0;
			switch (eAnchors) {
			case NW:
				x = r.x;
				y = r.y;
				break;
			case NN:
				x = r.x + r.width / 2;
				y = r.y;
				break;
			case NE:
				x = r.x + r.width;
				y = r.y;
				break;
			case EE:
				x = r.x + r.width;
				y = r.y + r.height / 2;
				break;
			case SE:
				x = r.x + r.width;
				y = r.y + r.height;
				break;
			case SS:
				x = r.x + r.width / 2;
				y = r.y + r.height;
				break;
			case SW:
				x = r.x;
				y = r.y + r.height;
				break;
			case WW:
				x = r.x;
				y = r.y + r.height / 2;
				break;
			case RR:
				x = r.x + r.width / 2;
				y = r.y - 50;
				break;
			}
			x = x - dw;
			y = y - dh;
			this.anchors.get(eAnchors.ordinal()).setFrame(x, y, w, h);

		}
	}
}
