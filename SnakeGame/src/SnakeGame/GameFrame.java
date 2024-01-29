package SnakeGame;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{
	GameFrame()
	{
		GamePanel  panel = new GamePanel();
		this.add(panel);
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();		// size the JFrame automatically to the size of the widgets within the page
		this.setVisible(true);
		this.setLocationRelativeTo(null); // Sets the location of the window relative to the specified component
	}
	
}
