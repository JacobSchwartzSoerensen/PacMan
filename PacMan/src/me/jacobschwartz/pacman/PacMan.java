package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class PacMan implements Paintable{
	
	private Level level;
	
	private int currentDirection = 3, newDirection = 3;
	public void setNewDirection(int dir){ newDirection = dir; }
	
	private double speed = 60; //pixels per second
	private long time;
	private double elapsedTime;
	private int c_moveX = 0, c_moveY = 0, n_moveX = 0, n_moveY = 0;
	
	private Rectangle2D collisionbox = new Rectangle2D.Double();
	private Color color = new Color(255,150,0);
	
	
	public PacMan(int x, int y, Level level){
		
		this.level = level;
		
		collisionbox.setRect(x*20, y*20, 20, 20);
		
		time = System.currentTimeMillis();
		
	}
	
	public void render(){
		
		//Calculating the time since last run
		elapsedTime = (System.currentTimeMillis() - time)/1000.0;
		time = System.currentTimeMillis();
		
		//Translating directions to positive and negative x and y directions
		
		switch(currentDirection){
			case 0:
				c_moveX = 0;
				c_moveY = -1;
				break;
			case 1:
				c_moveX = 1;
				c_moveY = 0;
				break;
			case 2:
				c_moveX = 0;
				c_moveY = 1;
				break;
			case 3:
				c_moveX = -1;
				c_moveY = 0;
				break;
		}
		
		switch(newDirection){
			case 0:
				n_moveX = 0;
				n_moveY = -1;
				break;
			case 1:
				n_moveX = 1;
				n_moveY = 0;
				break;
			case 2:
				n_moveX = 0;
				n_moveY = 1;
				break;
			case 3:
				n_moveX = -1;
				n_moveY = 0;
				break;
		}
		
		//If there is clear space in new direction, change direction, else keep old direction if that is clear. If both are blocked, stop.
		if(!level.isSolidColliding(new Rectangle((int)(collisionbox.getX()+(n_moveX*((elapsedTime*speed)+1))), (int)(collisionbox.getY()+(n_moveY*((elapsedTime*speed)+1))), (int)collisionbox.getWidth(), (int)collisionbox.getHeight()))){
			
			currentDirection = newDirection;
			collisionbox.setRect(collisionbox.getX()+(n_moveX*(elapsedTime*speed)), collisionbox.getY()+(n_moveY*(elapsedTime*speed)), collisionbox.getWidth(), collisionbox.getHeight());
			
		}else if(!level.isSolidColliding(new Rectangle((int)(collisionbox.getX()+(c_moveX*(elapsedTime*speed))), (int)(collisionbox.getY()+(c_moveY*(elapsedTime*speed))), (int)collisionbox.getWidth(), (int)collisionbox.getHeight()))){
			
			collisionbox.setRect(collisionbox.getX()+(c_moveX*(elapsedTime*speed)), collisionbox.getY()+(c_moveY*(elapsedTime*speed)), collisionbox.getWidth(), collisionbox.getHeight());
			
		}
		
		//Handling collisions with non-solid objects
		level.handleCollision(collisionbox.getBounds());
		
	}

	@Override
	public boolean isDead() {
		//Tells the list manager whether to remove this object
		return false;
	}

	@Override
	public void paint(Graphics2D g2d) {
		
		g2d.setColor(color);
		g2d.fillRect((int)collisionbox.getX(), (int)collisionbox.getY(), (int)collisionbox.getWidth(), (int)collisionbox.getHeight());
		
	}
	
}
