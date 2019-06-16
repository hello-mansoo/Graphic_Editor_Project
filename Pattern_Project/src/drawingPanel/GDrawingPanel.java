package drawingPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.GGroup;
import shape.GPolygon;
import shape.GShape;
import shape.GShape.EOnState;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel {

	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private MouseHandler mouseHandler;
	private Clipboard clipboard;
	
	private Vector<GShape> shapeVector;
	public Vector<GShape> getShapeVector() {
		return shapeVector;
	}
	
	@SuppressWarnings("unchecked")
	public void restoreShapeVector(Object shapeVector) {
		if(shapeVector == null) {
			this.shapeVector.clear();
		} else {
			this.shapeVector = (Vector<GShape>)shapeVector;
		}
		this.repaint();
	}

	// working variables
	private enum EActionState {eReady, eTransforming};
	private EActionState eActionState;
	
	private boolean updated;
	public boolean isUpdated() { return this.updated; }
	public void setUpdated(boolean updated) { this.updated = updated; }
	
	private GShape currentShape;
	private GTransformer transformer;
	private GShape currentTool;
	public void setCurrentTool(EToolBar currentTool) {
		this.currentTool = currentTool.getShape();
	}
	
	public GDrawingPanel() {
		this.eActionState = EActionState.eReady;
		this.updated = false;

		this.setForeground(Color.BLACK);
		this.setBackground(Color.WHITE);

		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);

		this.clipboard = new Clipboard();
		
		this.shapeVector = new Vector<GShape>();
		this.transformer = null;
	}
	
	public void initialize() {
		
	}
	
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D)graphics;
		
		super.paint(graphics2d);

		for (GShape shape : this.shapeVector) {
			shape.draw(graphics2d);
		}
		
		if(this.currentShape != null) {
			this.currentShape.draw(graphics2d);
		}
	}
	
	private void clearSelected() {
		for(GShape shape: this.shapeVector) {
			shape.setSelected(false);
		}
	}
	
	private EOnState onShape(int x, int y) {
		this.currentShape = null;
		for(GShape shape: this.shapeVector) {
			EOnState eOnState = shape.onShape(x, y);
			if(eOnState != null) {
				this.currentShape = shape;
				return eOnState;
			}
		}
		//this.clearSelected();
		return null;
	}
	
	private void defineActionState(int x, int y) {
		EOnState eOnState = onShape(x, y);
		if(eOnState == null) {
			this.clearSelected();
			this.transformer = new GDrawer();
		} else {
			if(!this.currentShape.isSelected()) {
				this.clearSelected();
				this.currentShape.setSelected(true);
			}
			switch(eOnState) {
			case eOnShape:
				this.transformer = new GMover();
				break;
			case eOnResize:
				this.transformer = new GResizer();
				break;
			case eOnRotate:
				this.transformer = new GRotator();
				break;
			default:
				//exception
				this.eActionState = null;
				break;
			}
		}
	}
	
	private void initTransforming(int x, int y) {
		if(this.transformer instanceof GDrawer) {
			this.currentShape = this.currentTool.newInstance();
		}
		this.transformer.setgShape(this.currentShape);
		this.transformer.initTransforming((Graphics2D)this.getGraphics(), x, y);
	}

	private void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D)this.getGraphics();
		this.transformer.keepTransforming(graphics2d, x, y);
		this.repaint();
	}

	private void finishTransforming(int x, int y) {
		this.transformer.finishTransforming((Graphics2D)this.getGraphics(), x, y);
		if(this.transformer instanceof GDrawer) {
			if(this.currentShape instanceof GGroup) {
				((GGroup)(this.currentShape)).contains(this.shapeVector);
			} else {
				this.shapeVector.add(this.currentShape);
			}
		}
		
		this.repaint();
		this.updated = true;
	}
	
	public void cut() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for(int i=this.shapeVector.size()-1; i>=0; i--) {
			if(this.shapeVector.get(i).isSelected()) {
				selectedShapes.add(this.shapeVector.get(i));
				this.shapeVector.remove(i);
			}
		}
		this.clipboard.setContents(selectedShapes);
		this.repaint();
	}

	public void copy() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for(GShape shape:this.shapeVector) {
			if(shape.isSelected()) {
				selectedShapes.add(shape);
			}
		}
		this.clipboard.setContents(selectedShapes);
		this.repaint();
	}

	public void paste() {
		Vector<GShape> shapes = this.clipboard.getContents();
		for(GShape shape:shapes)shape.verticalPaste();
		this.shapeVector.addAll(shapes);
		this.clipboard.setContents(shapes);
		this.clearSelected();
		repaint();
	}
	
	public void fill(Color color) {
		for(GShape shape:this.shapeVector) {
			if(shape.isSelected()) {
				shape.setColor(color);
			}
		}
		this.repaint();
	}
	
	public void stroke(int stroke) {
		for(GShape shape:this.shapeVector) {
			if(shape.isSelected()) {
				shape.setStroke(stroke);
			}
		}
		this.repaint();
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}
		public void mouse2Clicked(MouseEvent e) {
			if (eActionState == EActionState.eTransforming) {
				if(currentTool instanceof GPolygon) {
					eActionState = EActionState.eReady;
				}
			}
		}
		public void mouse1Clicked(MouseEvent e) {
			if (eActionState == EActionState.eReady) {
				if (currentTool instanceof GPolygon) {
				}
			} else if (eActionState == EActionState.eTransforming) {
				if (currentTool instanceof GPolygon) {
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			EOnState eOnState = onShape(e.getX(),e.getY());
			if (eActionState == EActionState.eTransforming) {
				if(currentTool instanceof GPolygon) {	
					
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eActionState == EActionState.eReady) {
				defineActionState(e.getX(), e.getY());
				initTransforming(e.getX(), e.getY());
				eActionState = EActionState.eTransforming;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eActionState == EActionState.eTransforming) {
				finishTransforming(e.getX(), e.getY());
				eActionState = EActionState.eReady;
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eActionState == EActionState.eTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
}
