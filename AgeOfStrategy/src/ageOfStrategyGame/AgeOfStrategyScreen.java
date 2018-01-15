package ageOfStrategyGame;

import java.awt.*;
import javax.swing.*;

public class AgeOfStrategyScreen extends JPanel implements Runnable
{
	public boolean isActive;
	Graphics2D G2D;
	
	private static final long serialVersionUID = 1L;

	double gObject[][];
	
	double cameraPosition[] = new double[3];
	double cameraVerticalAngle;
	double cameraHorizontalAngle;
	
	int width = this.getWidth();
	int height = this.getHeight();
	
	// --- Drawing variables ---
	
	int LinePool[][];
	
	AgeOfStrategyScreen()
	{
		System.out.println("camera created");
		cameraPosition[0] = 0;
		cameraPosition[1] = 600.0;
		cameraPosition[2] = 500.0;
		
		isActive = true;
		Thread AgeOfStartegyScreen = new Thread(this, "Update screen thread");
		AgeOfStartegyScreen.start();
	}
	
	void setDrawingPool()
	{
		
	}
	
	void draw(Graphics G)
	{
		G2D = (Graphics2D) G;
		G2D.setColor(Color.BLACK);
		
		G2D.drawString(this.getWidth() + " " + this.getHeight(), 10, 20);
	}
	
	void lockOnMiddle(boolean lock)
	{
		
	}
	
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		draw(G); 
	}
	
	void getWorldImage(double gObject[][])
	{
		try
		{
			this.gObject = gObject;
		}
		catch(NullPointerException Ex)
		{
			 
		}
	}
	
	int getMaxDimension()
	{
		
		return 0;
	}
	
	public void printSize()
	{
		System.out.println("Camera x:" + this.getWidth() + " y:" + this.getHeight());
	}
	
	public void run()
	{
		
		while(isActive)
		{
			
			repaint();
		}
	}
}
