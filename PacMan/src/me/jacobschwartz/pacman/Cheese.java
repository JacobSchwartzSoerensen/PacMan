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
			collisionbox.x = (x*20)+2;
			collisionbox.y = (y*20)+2;
			collisionbox.width = 16;
			collisionbox.height = 16;
			color = new Color(255,205,0);
		}else{
			collisionbox.x = (x*20)+5;
			collisionbox.y = (y*20)+5;
			collisionbox.width = 10;
			collisionbox.height = 10;
			color = new Color(255,255,0);
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
	public boolean onCollide() {
		
		System.out.println("colliding");
		
		level.addPoints(points);
		isDead = true;
		
		return false;
	}

	@Override
	public boolean isDead() {
		
		return isDead;
	}

}
