package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawingPanel.GDrawingPanel;
import global.Constants.EMainFrame;
import menu.GMenuBar;
import toolbar.GToolBar;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		// attribute
		this.setLocation(EMainFrame.x.getValue(), EMainFrame.y.getValue());
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		// components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		//associations
		this.menuBar.associate(this.drawingPanel);
		this.toolBar.associate(this.drawingPanel);
				
		//initialize
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
		
	}
}
