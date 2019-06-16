package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import drawingPanel.GDrawingPanel;
import global.Constants.EEditMenu;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private GDrawingPanel drawingPanel;

	public GEditMenu(String text) {
		super(text);

		ActionHandler actionHandler = new ActionHandler();

		for (EEditMenu eMenuItem : EEditMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			add(menuItem);
			
			switch (eMenuItem) {
			case redo:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
				break;
				
			case undo:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
				break;
				
			case cut:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
				break;
				
			case copy:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
				break;
				
			case paste:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
				break;
				
			case group:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
				break;
				
			case ungroup:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
				break;
			
			case fill:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
				break;
				
			case delete:
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK));
				break;
			}
		}
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public void initialize() {
		
	}
	
	private void invokeMethod(String name) {
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public void undo() {
//		this.drawingPanel.undo();
	}
	
	public void redo() {
//		this.drawingPanel.redo();
	}
	
	public void cut() {
		this.drawingPanel.cut();
	}
	
	public void copy() {
		this.drawingPanel.copy();
	}
	
	public void paste() {
		this.drawingPanel.paste();
	}
	
	public void group() {
//		this.drawingPanel.group();
	}
	
	public void ungroup() {
//		this.drawingPanel.ungroup();
	}
	
	public void fill() {
		Color color = JColorChooser.showDialog(this.drawingPanel, "Set Color", Color.WHITE);
		this.drawingPanel.fill(color);
	}
	
	public void delete() {
		this.drawingPanel.delete();
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			invokeMethod(event.getActionCommand());
		}
	}
}
