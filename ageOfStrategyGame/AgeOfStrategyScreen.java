package ageOfStrategyGame;

import java.awt.*;
import javax.swing.*;
import gameSourceLib.*;

public class AgeOfStrategyScreen extends JPanel
{
	
	double side[][]; 
		
	AgeOfStrategyScreen()
	{
		System.out.println("camera created");
	}
	
	void draw(Graphics G)
	{
		Graphics2D G2D = (Graphics2D) G;
		G2D.setColor(Color.BLACK);
		
	}
	
	public void paintComponent(Graphics G)
	{
		super.paintComponents(G);
		draw(G);
	}
	
	void getWorldImage(World world)
	{
		try
		{
			side = world.getPlanetaryGraphics();
		}
		catch(NullPointerException Ex)
		{
			
		}
	}
	
	public void printSize()
	{
		System.out.println("Camera x:" + this.getWidth() + " y:" + this.getHeight());
	}
}
