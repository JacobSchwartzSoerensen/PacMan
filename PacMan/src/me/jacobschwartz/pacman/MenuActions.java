package me.jacobschwartz.pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActions implements ActionListener {
	
	private GameEngine gameEngine;
	
	public MenuActions(GameEngine engine){
		
		gameEngine = engine;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()){
			case "start":
				gameEngine.startLevel();
				break;
			case "end":
				System.exit(0);
				break;
		}
		
	}

}
