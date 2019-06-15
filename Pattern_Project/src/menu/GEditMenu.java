package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			invokeMethod(event.getActionCommand());
		}
	}
}
