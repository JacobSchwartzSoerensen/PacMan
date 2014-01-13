package me.jacobschwartz.pacman;

public class GameEngine implements Runnable{
	
	private GraphicsEngine gfxEngine;
	private Level level;
	private Thread renderThread;
	private boolean renderThreadRunning = false;
	
	public GameEngine(){
		
		gfxEngine = new GraphicsEngine(this);
		
		startMenu();
		
	}
	
	public void startMenu(){
		
		renderThreadRunning = false;
		
		gfxEngine.changePanel(new Menu(this));
		gfxEngine.repaint();
		
	}
	
	public void startLevel(){
		
		level = new Level(this);
		gfxEngine.changePanel(level);
		gfxEngine.repaint();
		
		renderThreadRunning = true;
		renderThread = new Thread(this);
		renderThread.start();
		
	}

	@Override
	public void run() {
		
		while(renderThreadRunning){
			
			if(level != null){
				
				level.render();
				
			}
			
			gfxEngine.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
