package drawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.GShape;
import shape.GShape.EOnState;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
//import transformer.GRotator;
import transformer.GTransformer;
import shape.GPolygon;;

public class GDrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	
	private MouseHandler mouseHandler;

	private Vector<GShape> shapeVector;
	public Vector<GShape> getShapeVector() { 
		return this.shapeVector;
	}
	public void restoreShapeVector(Object shapeVector) {
		if(shapeVector==null) {
			this.shapeVector.clear();
		} else {
			this.shapeVector = (Vector<GShape>) shapeVector;			
		}
		this.repaint();
	}
	
	//working variable
	private enum EActionState {eReady, eTransforming}; // cmc占쏙옙 n占쏙옙占쏙옙트 占쏙옙占쏙옙占쏙옙占쏙옙占� pdr占쏙옙 two占쏙옙占쏙옙트 占쏙옙占쏙옙占쏙옙占쏙옙占� 占쌕뀐옙占쏙옙占쏙옙
	private EActionState eActionState; // 占쌓몌옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占싶놂옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싻몌옙 ***占쏙옙 占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쌔억옙占싼댐옙.
	
	private boolean updated;
	public boolean isUpdated() { return this.updated;}
	public void setUpdated(boolean updated) {this.updated = updated;}
	
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

		this.shapeVector = new Vector<GShape>();
		this.transformer = null;
	}

	public void initialize() {

	}

	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		super.paint(graphics2d);

		for (GShape shape : this.shapeVector) {
			shape.draw(graphics2d);
		}
	}

	private void drawShape() {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(getBackground());
		this.currentShape.draw(graphics2d);
	}

	private EOnState onShape(int x, int y) {
		this.currentShape = null;
		for (GShape shape : this.shapeVector) {
			EOnState eOnState = shape.onShape(x, y);
			if (eOnState != null) {
				this.currentShape = shape;
				return eOnState;
			}
		}
		return null;
	}

	private void defineActionState(int x, int y) {		
		EOnState eOnState = onShape(x, y);
		if (eOnState == null) {
			this.transformer = new GDrawer();
		} else {
			switch (eOnState) {
			case eOnShape:
				this.transformer = new GMover();
				break;
			case eOnResize:
				this.transformer = new GResizer();
				break;
			case eOnRotate:
				//this.transformer = new GRotator();
				break;
			default:
				// exception
				this.eActionState = null;
				break;
			}
		}
	}

	private void initTransforming(int x, int y) {
		if(this.transformer instanceof GDrawer) {
			this.currentShape = this.currentTool.clone();
		}
		this.transformer.setgShape(this.currentShape);
		this.transformer.initTransforming((Graphics2D)this.getGraphics(), x, y);
	}

	private void keepTransforming(int x, int y) {
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		graphics2D.setXORMode(this.getBackground());
		this.transformer.keepTransforming(graphics2D, x, y);
	}

	private void finishTransforming(int x, int y) {
		this.transformer.finishTransforming((Graphics2D)this.getGraphics(), x, y);	
		if(this.transformer instanceof GDrawer) {
			this.shapeVector.add(this.currentShape);
		}
	}

	private class MouseHandler implements MouseListener, MouseMotionListener { //state transition mapping
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		private void mouse2Clicked(MouseEvent e) {
			if (eActionState == EActionState.eTransforming) {
				if (currentTool instanceof GPolygon) {
					eActionState = EActionState.eReady;
				}
			}
		}

		private void mouse1Clicked(MouseEvent e) {
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
			if (eActionState == EActionState.eTransforming) {
				if (currentTool instanceof GPolygon) {
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
