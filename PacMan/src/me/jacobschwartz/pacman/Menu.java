package me.jacobschwartz.pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;
	private GameEngine gameEngine;
	private Color bgColor = new Color(0,0,0);
	private Font nameFont = new Font("Verdana", Font.BOLD, 80);
	private Color nameColor = new Color(255,255,255);
	
	private JButton b_startGame;
	private JButton b_endGame;
	private MenuActions menuActions;
	
	public Menu(GameEngine engine){
		
		gameEngine = engine;
		
		menuActions = new MenuActions(gameEngine);
		
		setLayout(null);
		this.setPreferredSize(new Dimension(800, 600));
		
		b_startGame = new JButton("Start Game");
		b_startGame.setBounds(500, 200, 100, 25);
		b_startGame.setActionCommand("start");
		b_startGame.addActionListener(menuActions);
		add(b_startGame);
		
		b_endGame = new JButton("Quit Game");
		b_endGame.setBounds(500, 250, 100, 25);
		b_endGame.setActionCommand("end");
		b_endGame.addActionListener(menuActions);
		add(b_endGame);
		
		setBackground(bgColor);
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Drawing name
		g2d.setColor(nameColor);
		g2d.setFont(nameFont);
		g2d.drawString("PacMan", 25, 200);
		
	}

}
