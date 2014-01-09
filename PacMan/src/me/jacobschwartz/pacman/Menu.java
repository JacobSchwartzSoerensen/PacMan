package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;
	private GameEngine gameEngine;
	private Color bgColor = new Color(0,0,0);
	private Font nameFont = new Font("Verdana", Font.BOLD, 80);
	private Color nameColor = new Color(255,255,255);
	
	public Menu(GameEngine engine){
		
		gameEngine = engine;
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		//Drawing background
		g2d.setColor(bgColor);
		g2d.fillRect(0, 0, 800, 600);
		
		//Drawing name
		g2d.setColor(nameColor);
		g2d.setFont(nameFont);
		g2d.drawString("PacMan", 25, 200);
		
	}

}
