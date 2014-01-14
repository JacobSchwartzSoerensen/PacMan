package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Level extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private GameEngine gameEngine;
	private LevelKeyListener keyListener;
	
	private List<Collideable> collisionList = new ArrayList<Collideable>();
	private List<Paintable> paintList = new ArrayList<Paintable>();
	private List<Intersection> intersectionList = new ArrayList<Intersection>();
	private PacMan pacman;
	private Ghost ghost;
	
	private int points = 0;
	public void addPoints(int points){ this.points += points; }
	public Point getPacManPos(){ return new Point(pacman.getX(), pacman.getY()); }
	
	//0 = empty
	//1 = wall
	//2 = cheese
	//3 = Big Cheese
	//4 = PacMan
	//5 = Intersection
	//6 = Intersection + cheese
	//7 = Intersection + big cheese
	//8 = Ghost
	private final byte levelData[] = {
		1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
		1,6,2,2,2,6,2,2,2,6,1,6,2,2,2,6,2,2,2,6,1,
		1,2,1,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,1,2,1,
		1,3,1,0,1,2,1,0,1,2,1,2,1,0,1,2,1,0,1,3,1,
		1,2,1,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,1,2,1,
		1,6,2,2,2,6,2,6,2,6,2,6,2,6,2,6,2,2,2,6,1,
		1,2,1,1,1,2,1,2,1,1,1,1,1,2,1,2,1,1,1,2,1,
		1,2,1,1,1,2,1,2,1,1,1,1,1,2,1,2,1,1,1,2,1,
		1,6,2,2,2,6,1,6,2,6,1,6,2,6,1,6,2,2,2,6,1,
		1,1,1,1,1,2,1,1,1,0,1,0,1,1,1,2,1,1,1,1,1,
		0,0,0,0,1,2,1,5,0,5,5,5,0,5,1,2,1,0,0,0,0,
		0,0,0,0,1,2,1,0,1,0,0,0,1,0,1,2,1,0,0,0,0,
		1,1,1,1,1,2,1,0,1,0,8,0,1,0,1,2,1,1,1,1,1,
		0,0,0,0,0,6,0,5,1,5,5,5,1,5,0,6,0,0,0,0,0,
		1,1,1,1,1,2,1,0,1,1,1,1,1,0,1,2,1,1,1,1,1,
		0,0,0,0,1,2,1,5,0,0,0,0,0,5,1,2,1,0,0,0,0,
		0,0,0,0,1,2,1,0,1,1,1,1,1,0,1,2,1,0,0,0,0,
		1,1,1,1,1,2,1,0,1,1,1,1,1,0,1,2,1,1,1,1,1,
		1,6,2,2,2,6,2,6,2,6,1,6,2,6,2,6,2,2,2,6,1,
		1,2,1,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,1,2,1,
		1,7,2,6,1,6,2,6,2,6,4,6,2,6,2,6,1,6,2,7,1,
		1,1,1,2,1,2,1,2,1,1,1,1,1,2,1,2,1,2,1,1,1,
		1,1,1,2,1,2,1,2,1,1,1,1,1,2,1,2,1,2,1,1,1,
		1,6,2,6,2,6,1,6,2,6,1,6,2,6,1,6,2,6,2,6,1,
		1,2,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,2,1,
		1,6,2,2,2,2,2,2,2,6,2,6,2,2,2,2,2,2,2,6,1,
		1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
	};
	
	public Level(GameEngine engine){
		
		gameEngine = engine;
		keyListener = new LevelKeyListener(this);
		this.addKeyListener(keyListener);
		
		buildBoard();
		
	}
	
	private void handleKeys(){
		
		switch(keyListener.getCurrentKeyCode()){
			case KeyEvent.VK_ESCAPE:
				gameEngine.startMenu();
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				pacman.setNewDirection(0);
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				pacman.setNewDirection(1);
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				pacman.setNewDirection(2);
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				pacman.setNewDirection(3);
				break;
		}
		
	}
	
	public void buildBoard(){
		
		Wall wall;
		Cheese cheese;
		Intersection intersection;
		
		for(int i = 0; i < levelData.length; i++){
			
			switch(levelData[i]){
				case 1:
					wall = new Wall(i-((i/21)*21),i/21);
					collisionList.add(wall);
					paintList.add(wall);
					break;
				case 2:
					cheese = new Cheese(i-((i/21)*21),i/21, false, this);
					collisionList.add(cheese);
					paintList.add(cheese);
					break;
				case 3:
					cheese = new Cheese(i-((i/21)*21),i/21, true, this);
					collisionList.add(cheese);
					paintList.add(cheese);
					break;
				case 4:
					pacman = new PacMan(i-((i/21)*21),i/21, this);
					paintList.add(pacman);
					break;
				case 5:
					intersection = new Intersection(i-((i/21)*21),i/21);
					intersectionList.add(intersection);
					paintList.add(intersection);
					break;
				case 6:
					intersection = new Intersection(i-((i/21)*21),i/21);
					intersectionList.add(intersection);
					paintList.add(intersection);
					cheese = new Cheese(i-((i/21)*21),i/21, false, this);
					collisionList.add(cheese);
					paintList.add(cheese);
					break;
				case 7:
					intersection = new Intersection(i-((i/21)*21),i/21);
					intersectionList.add(intersection);
					paintList.add(intersection);
					cheese = new Cheese(i-((i/21)*21),i/21, true, this);
					collisionList.add(cheese);
					paintList.add(cheese);
					break;
				case 8:
					ghost = new Ghost(i-((i/21)*21),i/21, this);
					collisionList.add(ghost);
					paintList.add(ghost);
					break;
			}
			
		}
		
	}
	
	public void listCleanUp(){
		
		for(int i = 0; i < collisionList.size(); i++){
			
			if(collisionList.get(i).isDead()){
				
				collisionList.remove(i);
				
			}
			
		}
		
		for(int i = 0; i < paintList.size(); i++){
			
			if(paintList.get(i).isDead()){
				
				paintList.remove(i);
				
			}
			
		}
		
	}
	
	public boolean isSolidColliding(Rectangle rect){
		
		for(int i = 0; i < collisionList.size(); i++){
			
			if(collisionList.get(i).isSolidColliding(rect)){
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public void handleCollision(Rectangle rect){
		
		for(int i = 0; i < collisionList.size(); i++){
			
			collisionList.get(i).handleCollision(rect);
			
		}
		
	}
	
	public boolean isAtIntersection(Rectangle rect){
		
		for(int i = 0; i < intersectionList.size(); i++){
			
			if(intersectionList.get(i).isColliding(rect)){
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public void render(){
		
		listCleanUp();
		handleKeys();
		pacman.render();
		ghost.render();
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, 800, 600);
		
		for(int i = 0; i < paintList.size(); i++){
			
			paintList.get(i).paint(g2d);
			
		}
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("Points: "+points, 500, 100);
		
		
	}
	
}
