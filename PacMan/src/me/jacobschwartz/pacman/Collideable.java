package me.jacobschwartz.pacman;

import java.awt.Rectangle;

public interface Collideable {
	
	public boolean isDead();
	
	public void handleCollision(Rectangle rect);
	public boolean isSolidColliding(Rectangle rect);
	
}
