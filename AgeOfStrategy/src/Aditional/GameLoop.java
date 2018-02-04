package Aditional;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class GameLoop implements Runnable
{
	GameEngine GEngine;
	GameDisplay GDisplay;
	
	EventPool OutputPool = new EventPool("Game events pool");
	EventPool InputPool = new EventPool("Input pool");
	
	GameLoop()
	{
		JFrame GameWindow = new JFrame();
		GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameWindow.setSize(500, 500);
		GameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		GameWindow.getContentPane().setBackground(Color.BLACK);
		
		GEngine = new GameEngine(InputPool, OutputPool);
		GDisplay = new GameDisplay(InputPool, OutputPool);
		GDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		GameWindow.add(GDisplay);
		GameWindow.addKeyListener(GDisplay);
		GameWindow.setVisible(true);
		
		Thread loop = new Thread(this, "loop");
		loop.start();
	}
	
	public static void main(String Args[])
	{
		SwingUtilities.invokeLater(new Runnable() {public void run() {new GameLoop();}});
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(50);
			}
			catch(InterruptedException Ex)
			{
				
			}
		}
	}
}
