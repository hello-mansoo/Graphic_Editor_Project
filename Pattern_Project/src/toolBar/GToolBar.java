package toolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import drawingPanel.GDrawingPanel;
import global.Constants.EToolBar;

public class GToolBar extends JToolBar {

	//attribute
	private static final long serialVersionUID = 1L;

	//components
	private Vector<JRadioButton> buttons; 
	
	//associations
	private GDrawingPanel drawingPanel;
	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}	
	public GToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		
		this.buttons = new Vector<JRadioButton>();
		ActionHandler actionHandler = new ActionHandler();
		for (EToolBar eToolBar: EToolBar.values()) {
			JRadioButton button = new JRadioButton(eToolBar.getText());
			button.setActionCommand(eToolBar.name());
			button.addActionListener(actionHandler);
			this.buttons.add(button);
			this.add(button);
			buttonGroup.add(button);
			}
		
		}

	public void initialize() {
		this.buttons.get(EToolBar.rectangle.ordinal()).doClick();
	}
	
	private class ActionHandler implements ActionListener	{

		@Override
		public void actionPerformed(ActionEvent event) {
			drawingPanel.setCurrentTool(EToolBar.valueOf(event.getActionCommand()));
			
		}	
	}

}
