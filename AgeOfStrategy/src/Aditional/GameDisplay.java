package Aditional;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.Random;

public class GameDisplay extends JLabel implements ComponentListener, KeyListener
{
	double gridSpacing;
	double vertSize;
	double horSize;
	int pixelSize;
	
	EventPool InputPool;
	EventPool OutputPool;
	
	Graphics2D G2D;
	int[][][] DisplaingArray = new int[320][240][3];
	
	GameDisplay(EventPool InputPool, EventPool OutputPool)
	{
		this.InputPool = InputPool;
		this.OutputPool = OutputPool;
		
		InputPool.addEvent("Move Up");
		InputPool.addEvent("Move Down");
		InputPool.addEvent("Turn Left");
		InputPool.addEvent("Turn Right");
		
		addComponentListener(this);
		addKeyListener(this);
		update();
	}
	
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		draw(G);
	}
	
	void draw(Graphics G)
	{

		G2D = (Graphics2D) G;
		
		for(int i = 0; i < 320; i++)
		{
			for(int j = 0; j < 240; j++)
			{
				G2D.setColor(new Color(DisplaingArray[i][j][0], DisplaingArray[i][j][0], DisplaingArray[i][j][0]));
				G2D.fillRect((int) (gridSpacing * i), (int) (gridSpacing * j), (int) (pixelSize), (int) (pixelSize));
			}
			
		}
	}
	
	public void update()
	{
		for(int i = 0; i < 320; i++)
		{
			for(int j = 0; j < 240; j++)
			{
				
			}
		}
		repaint();
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) 
	{
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) 
	{
				
	}

	@Override
	public void componentResized(ComponentEvent arg0) 
	{
		horSize = this.getWidth();
		vertSize = this.getHeight();
		
		if((horSize / vertSize) > 1.25)
		{
			gridSpacing = vertSize / 240; 
		}
		else
		{
			gridSpacing = horSize / 320;
		}
		
		if(gridSpacing <= 1) pixelSize = 1;
		else if(gridSpacing <= 2) pixelSize = 2;
		else if(gridSpacing <= 3) pixelSize = 3;
		else if(gridSpacing <= 4) pixelSize = 4;
		else if(gridSpacing <= 5) pixelSize = 5;
		
		repaint();
	}

	@Override
	public void componentShown(ComponentEvent arg0) 
	{
		
	}
	
	public void endLoop()
	{
		
	}

	@Override
	public void keyPressed(KeyEvent arg)
	{
		KeyEventHandler(arg);
	}

	@Override
	public void keyReleased(KeyEvent arg) 
	{
		
	}

	@Override
	public void keyTyped(KeyEvent arg) 
	{
		
	}
	
	void KeyEventHandler(KeyEvent Ke)
	{
		switch(Ke.getKeyCode())
		{
			case 87:
				System.out.println("Forward");
				InputPool.setEventState("Move Up", true);
				break;
			case 83:
				System.out.println("Backward");
				InputPool.setEventState("Move Down", true);
				break;
			case 65:
				System.out.println("Turn Left");
				InputPool.setEventState("Turn Right", true);
				break;
			case 68:
				System.out.println("Turn Right");
				InputPool.setEventState("TurnLeft", true);
				break;
			default:
				System.out.println(Ke.getExtendedKeyCode());
				break;
		}
	}
}
