package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Level extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private GameEngine gameEngine;
	private LevelKeyListener keyListener;
	
	public Level(GameEngine engine){
		
		gameEngine = engine;
		keyListener = new LevelKeyListener(this);
		this.addKeyListener(keyListener);
		
	}
	
	private void handleKeys(){
		
		switch(keyListener.getCurrentKeyCode()){
			case KeyEvent.VK_ESCAPE:
				gameEngine.startMenu();
				break;
		}
		
	}
	
	public void render(){
		
		handleKeys();
		
	}
	
	public void paintBoard(Graphics2D g2d){
		
		
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, 800, 600);
		
		paintBoard(g2d);
		
	}
	
}
