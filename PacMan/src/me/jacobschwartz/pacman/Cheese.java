package me.jacobschwartz.pacman;

import java.awt.Graphics2D;

public class Cheese implements Collideable, Paintable {
	
	private boolean bonusCheese;
	private Level level;
	private boolean isDead = false;
	
	public Cheese(boolean bonus, Level level){
		
		bonusCheese = bonus;
		this.level = level;
		
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCollide() {
		
		
		
		return false;
	}

	@Override
	public boolean isDead() {
		
		return isDead;
	}

}
