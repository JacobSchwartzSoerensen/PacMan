package me.jacobschwartz.pacman;

import java.awt.Graphics2D;

public interface Paintable {
	
	public boolean isDead();
	
	public void paint(Graphics2D g2d);
	
}
