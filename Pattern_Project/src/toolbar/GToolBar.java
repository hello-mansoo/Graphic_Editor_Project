package toolbar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import drawingPanel.GDrawingPanel;
import global.Constants.EToolBar;

public class GToolBar extends JToolBar {
	
	//attributes
	private static final long serialVersionUID = 1L;
	
	//components
	private Vector<JRadioButton> buttons;
	private JTextField stroke;
	
	//associations
	private GDrawingPanel drawingPanel;
	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	public GToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		this.buttons = new Vector<JRadioButton>();
		
		ActionHandler actionHandler = new ActionHandler();
		
		for(EToolBar eToolBar: EToolBar.values()) {
			JRadioButton button = new JRadioButton();
			button.setActionCommand(eToolBar.name());
			button.setIcon(new ImageIcon(eToolBar.getImage()));
			button.addActionListener(actionHandler);
			this.buttons.add(button);
			this.add(button);
			buttonGroup.add(button);
		}
		
		JLabel strokeLabel = new JLabel("|| 선 굵기: ");
		strokeLabel.setFont(new Font("Gothic", Font.PLAIN, 18));
		StrokeHandler strokeHandler = new StrokeHandler();
		stroke = new JTextField();
		stroke.addActionListener(strokeHandler);
		
		this.add(strokeLabel);
		this.add(stroke);
	}
	
	public void initialize() {
		this.buttons.get(EToolBar.rectangle.ordinal()).doClick(); // 오디널은 그 앞 객체가 몇번째 인덱스에 있는지를 가져온다. 두클릭은 강제로 눌려지게.	
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			drawingPanel.setCurrentTool(EToolBar.valueOf(event.getActionCommand()));
		}
	}
	
	private class StrokeHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int pickStroke = Integer.parseInt(stroke.getText());
			drawingPanel.stroke(pickStroke);
			stroke.setFocusable(false);
			stroke.setFocusable(true);
		}
		
	}
}
