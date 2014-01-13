package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall implements Collideable, Paintable {
	
	private Rectangle collisionbox = new Rectangle();
	private int width = 20, height = 20;
	private Color color = new Color(0, 0, 255);
	
	
	public Wall(int x, int y){
		
		collisionbox.x = x*20;
		collisionbox.y = y*20;
		collisionbox.width = width;
		collisionbox.height = height;
		
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		
		g2d.setColor(color);
		g2d.fillRect(collisionbox.x, collisionbox.y, collisionbox.width, collisionbox.height);

	}

	@Override
	public void handleCollision(Rectangle rect) {
		
		
	}

	@Override
	public boolean isDead() {
		
		return false;
	}

	@Override
	public boolean isSolidColliding(Rectangle rect) {

		return collisionbox.intersects(rect) ? true : false;
	}

}
