package me.jacobschwartz.pacman;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsEngine extends JPanel{

	private static final long serialVersionUID = 1L;
	private JFrame window;
	private GameEngine gameEngine;
	
	public GraphicsEngine(GameEngine engine){
		
		gameEngine = engine;
		
		setLayout(null);
		setPreferredSize(new Dimension(800,600));
		
		window = new JFrame();
		window.setTitle("PacMan");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		
		window.add(this);
		
		window.setVisible(true);
		
	}
	
	public void changePanel(JPanel panel){
		
		this.removeAll();
		panel.setBounds(0, 0, 800, 600);
		this.add(panel);
		repaint();
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}

}
