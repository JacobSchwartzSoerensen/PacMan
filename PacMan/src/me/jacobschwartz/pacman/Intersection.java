package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Intersection implements Paintable {
	
	private Rectangle collisionbox = new Rectangle();
	private int width = 20, height = 20;
	private Color color = new Color(255, 0, 0, 80);
	
	
	public Intersection(int x, int y){
		
		collisionbox.x = x*20;
		collisionbox.y = y*20;
		collisionbox.width = width;
		collisionbox.height = height;
		
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		
		/*g2d.setColor(color);
		g2d.fillRect(collisionbox.x, collisionbox.y, collisionbox.width, collisionbox.height);*/

	}
	
	public boolean isColliding(Rectangle rect) {
		
		if(collisionbox.x == rect.x && collisionbox.y == rect.y){
			
			return true;
			
		}
		
		return false;
		
	}

	@Override
	public boolean isDead() {
		
		return false;
	}

}
