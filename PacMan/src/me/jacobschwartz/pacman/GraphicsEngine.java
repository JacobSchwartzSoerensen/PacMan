package me.jacobschwartz.pacman;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GraphicsEngine extends JPanel{

	private static final long serialVersionUID = 1L;
	private GameEngine gameEngine;
	private JPanel currentPanel;
	
	public GraphicsEngine(GameEngine engine){
		
		gameEngine = engine;
		currentPanel = new Menu(gameEngine);
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		currentPanel.paint(g);
		
	}

}