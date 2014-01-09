package me.jacobschwartz.pacman;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setTitle("PacMan");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		
		@SuppressWarnings("unused")
		GameEngine engine = new GameEngine(window);
		
		window.setVisible(true);

	}

}
