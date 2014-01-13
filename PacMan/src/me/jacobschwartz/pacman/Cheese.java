package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Cheese implements Collideable, Paintable {
	
	private Rectangle collisionbox = new Rectangle();
	
	private boolean bonusCheese;
	private Level level;
	private boolean isDead = false;
	private int points;
	
	private Color color;
	
	public Cheese(int x, int y, boolean bonus, Level level){
		
		if(bonus){
			collisionbox.x = (x*20)+4;
			collisionbox.y = (y*20)+4;
			collisionbox.width = 12;
			collisionbox.height = 12;
			color = new Color(255,205,0);
			points = 50;
		}else{
			collisionbox.x = (x*20)+8;
			collisionbox.y = (y*20)+8;
			collisionbox.width = 4;
			collisionbox.height = 4;
			color = new Color(255,255,0);
			points = 10;
		}
		
		bonusCheese = bonus;
		this.level = level;
		
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		
		g2d.setColor(color);
		
		g2d.fillOval(collisionbox.x, collisionbox.y, collisionbox.width, collisionbox.height);

	}

	@Override
	public void handleCollision(Rectangle rect) {
		
		if(collisionbox.intersects(rect)){
			level.addPoints(points);
			isDead = true;
		}
		
	}

	@Override
	public boolean isDead() {
		
		return isDead;
	}

	@Override
	public boolean isSolidColliding(Rectangle rect) {
		// TODO Auto-generated method stub
		return false;
	}

}
