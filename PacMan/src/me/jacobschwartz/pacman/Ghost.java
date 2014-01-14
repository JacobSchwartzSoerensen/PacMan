package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Ghost implements Paintable, Collideable{
	
	private int currentDirection = 0;
	private double speed = 60; //pixels per second
	private long time;
	private double elapsedTime;
	private int c_moveX = 0, c_moveY = 0;
	private Rectangle2D collisionbox = new Rectangle2D.Double();
	private Color color = new Color(0,255,0);
	private Level level;
	private int n_moveX1 = 0, n_moveY1 = 0,
				n_moveX2 = 0, n_moveY2 = 0,
				n_moveX3 = 0, n_moveY3 = 0;
	private int dist1, dist2, dist3;
	private int newX, newY;
	
	public Ghost(int x, int y, Level level){
		
		this.level = level;
		
		collisionbox.setRect(x*20, y*20, 20, 20);
		
		time = System.currentTimeMillis();
		
	}
	
	public void render(){
		
		//Calculating the time since last run
		elapsedTime = (System.currentTimeMillis() - time)/1000.0;
		time = System.currentTimeMillis();
		
		//Resetting the distances to high values
		dist1 = 9999;
		dist2 = 9999;
		dist3 = 9999;
		
		//If the ghost is not at an intersection continue in current direction(with collision checks of course), else find new directions and calculate the one with shortest distance to pacman
		if(!level.isAtIntersection(collisionbox.getBounds())){
			
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
			
			if(!level.isSolidColliding(new Rectangle((int)(collisionbox.getX()+(c_moveX*(elapsedTime*speed))), (int)(collisionbox.getY()+(c_moveY*(elapsedTime*speed))), (int)collisionbox.getWidth(), (int)collisionbox.getHeight()))){
				
				collisionbox.setRect(collisionbox.getX()+(c_moveX*(elapsedTime*speed)), collisionbox.getY()+(c_moveY*(elapsedTime*speed)), collisionbox.getWidth(), collisionbox.getHeight());
				
			}
		
		}else{
			
			//Calculation possible new directions, to avoid going back in the opposite direction
			switch(currentDirection){
				case 0:
					n_moveX1 = 0;
					n_moveY1 = -1; 
					
					n_moveX2 = 1;
					n_moveY2 = 0;
					
					n_moveX3 = -1;
					n_moveY3 = 0;
					break;
				case 1:
					n_moveX1 = 0;
					n_moveY1 = -1; 
					
					n_moveX2 = 1;
					n_moveY2 = 0;
					
					n_moveX3 = 0;
					n_moveY3 = 1;
					break;
				case 2:
					n_moveX1 = 1;
					n_moveY1 = 0; 
					
					n_moveX2 = 0;
					n_moveY2 = 1;
					
					n_moveX3 = -1;
					n_moveY3 = 0;
					break;
				case 3:
					n_moveX1 = 0;
					n_moveY1 = -1; 
					
					n_moveX2 = 0;
					n_moveY2 = 1;
					
					n_moveX3 = -1;
					n_moveY3 = 0;
					break;
			}
			
			System.out.println("-------------------------------------");
			
			//Testing if the new direction is free space, and calculate the distance at the new position
			//If the calculated distance is lower than the previous one, it will select that direction until a lower result is found
			if(!level.isSolidColliding(new Rectangle((int)(collisionbox.getX()+(n_moveX1*20)), (int)(collisionbox.getY()+(n_moveY1*20)), (int)collisionbox.getWidth(), (int)collisionbox.getHeight()))){
				
				newX = (int)(collisionbox.getX()+(n_moveX1*20));
				newY = (int)(collisionbox.getY()+(n_moveY1*20));
				dist1 = (int)Math.abs(Math.sqrt(Math.pow(level.getPacManPos().y-newY, 2)+Math.pow(level.getPacManPos().x-newX, 2)));
				
				System.out.println("Dist1: "+dist1);
				
				c_moveX = n_moveX1;
				c_moveY = n_moveY1;
				
				if(n_moveX1 == 0 && n_moveY1 == -1){
					
					currentDirection = 0;
					
				}else if(n_moveX1 == 1 && n_moveY1 == 0){
					
					currentDirection = 1;
				
				}else if(n_moveX1 == 0 && n_moveY1 == 1){
					
					currentDirection = 2;
				
				}else if(n_moveX1 == -1 && n_moveY1 == 0){
					
					currentDirection = 3;
				
				}
				
				
			}
			if(!level.isSolidColliding(new Rectangle((int)(collisionbox.getX()+(n_moveX2*20)), (int)(collisionbox.getY()+(n_moveY2*20)), (int)collisionbox.getWidth(), (int)collisionbox.getHeight()))){
				
				newX = (int)(collisionbox.getX()+(n_moveX2*20));
				newY = (int)(collisionbox.getY()+(n_moveY2*20));
				dist2 = (int)Math.abs(Math.sqrt(Math.pow(level.getPacManPos().y-newY, 2)+Math.pow(level.getPacManPos().x-newX, 2)));
				
				System.out.println("Dist2: "+dist2);
				
				if(dist2 < dist1){
					
					System.out.println("Dist 2 picked");
					
					c_moveX = n_moveX2;
					c_moveY = n_moveY2;
					
					if(n_moveX2 == 0 && n_moveY2 == -1){
						
						currentDirection = 0;
						
					}else if(n_moveX2 == 1 && n_moveY2 == 0){
						
						currentDirection = 1;
					
					}else if(n_moveX2 == 0 && n_moveY2 == 1){
						
						currentDirection = 2;
					
					}else if(n_moveX2 == -1 && n_moveY2 == 0){
						
						currentDirection = 3;
					
					}
					
				}
				
			}
			if(!level.isSolidColliding(new Rectangle((int)(collisionbox.getX()+(n_moveX3*20)), (int)(collisionbox.getY()+(n_moveY3*20)), (int)collisionbox.getWidth(), (int)collisionbox.getHeight()))){
				
				newX = (int)(collisionbox.getX()+(n_moveX3*20));
				newY = (int)(collisionbox.getY()+(n_moveY3*20));
				dist3 = (int)Math.abs(Math.sqrt(Math.pow(level.getPacManPos().y-newY, 2)+Math.pow(level.getPacManPos().x-newX, 2)));
				
				System.out.println("Dist3: "+dist3);
				
				if(dist3 < dist2 && dist3 < dist1){
					
					System.out.println("Dist 3 picked");
					
					c_moveX = n_moveX3;
					c_moveY = n_moveY3;
					
					if(n_moveX3 == 0 && n_moveY3 == -1){
						
						currentDirection = 0;
						
					}else if(n_moveX3 == 1 && n_moveY3 == 0){
						
						currentDirection = 1;
					
					}else if(n_moveX3 == 0 && n_moveY3 == 1){
						
						currentDirection = 2;
					
					}else if(n_moveX3 == -1 && n_moveY3 == 0){
						
						currentDirection = 3;
					
					}
					
				}
				
			}
			
			if(!level.isSolidColliding(new Rectangle((int)(collisionbox.getX()+(c_moveX*(elapsedTime*speed))), (int)(collisionbox.getY()+(c_moveY*(elapsedTime*speed))), (int)collisionbox.getWidth(), (int)collisionbox.getHeight()))){
				
				collisionbox.setRect(collisionbox.getX()+(c_moveX*(elapsedTime*speed)), collisionbox.getY()+(c_moveY*(elapsedTime*speed)), collisionbox.getWidth(), collisionbox.getHeight());
				
			}
			
		}
		
	}
	
	@Override
	public void handleCollision(Rectangle rect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSolidColliding(Rectangle rect) {
		
		return false;
	}

	@Override
	public boolean isDead() {
		
		return false;
	}

	@Override
	public void paint(Graphics2D g2d) {

		g2d.setColor(color);
		g2d.fillRect((int)collisionbox.getX(), (int)collisionbox.getY(), (int)collisionbox.getWidth(), (int)collisionbox.getHeight());
		
	}

}
