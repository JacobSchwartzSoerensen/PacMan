package me.jacobschwartz.pacman;

public class GameEngine {
	
	private GraphicsEngine GfxEngine;
	
	public GameEngine(){
		
		GfxEngine = new GraphicsEngine(this);
		
		startMenu();
		
	}
	
	public void startMenu(){
		
		GfxEngine.changePanel(new Menu(this));
		GfxEngine.repaint();
		
	}
	
	public void startLevel(){
		
		
		
	}
	
}
