package me.jacobschwartz.pacman;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameEngine {
	
	private JFrame window;
	private JPanel GfxEngine;
	
	public GameEngine(JFrame frame){
		
		window = frame;
		GfxEngine = new GraphicsEngine(this);
		
		window.add(GfxEngine);
		
		
	}
	
	public void startLevel(){
		
		
		
	}
	
}
