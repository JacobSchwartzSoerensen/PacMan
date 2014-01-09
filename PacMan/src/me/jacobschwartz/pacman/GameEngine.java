package me.jacobschwartz.pacman;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameEngine {
	
	private JFrame window;
	private GraphicsEngine GfxEngine;
	
	public GameEngine(JFrame frame){
		
		window = frame;
		GfxEngine = new GraphicsEngine(this);
		
		window.add(GfxEngine);
		
		startMenu();
		
	}
	
	public void startMenu(){
		
		GfxEngine.changePanel(new Menu(this));
		GfxEngine.repaint();
		
	}
	
	public void startLevel(){
		
		
		
	}
	
}
