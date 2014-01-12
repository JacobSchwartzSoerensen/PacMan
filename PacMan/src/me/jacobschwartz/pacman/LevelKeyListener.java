package me.jacobschwartz.pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelKeyListener implements KeyListener {
	
	private int currentKey;
	
	public int getCurrentKeyCode(){ return currentKey;}
	
	public LevelKeyListener(Level level){
		
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		currentKey = e.getKeyCode();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		currentKey = 0;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
		
	}

}
