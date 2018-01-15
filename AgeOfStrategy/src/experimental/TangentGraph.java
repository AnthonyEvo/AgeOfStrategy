package experimental;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TangentGraph extends JFrame
{
	Panel panel;
	
	public TangentGraph()
	{
		this.setSize(1100, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		panel = new Panel();
		this.add(panel);
	}	
}

class Panel extends JPanel
{
	int distance;
	int divisionValue;
	
	Panel()
	{
		
	}
	
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		draw(G);
	}
	
	void draw(Graphics G)
	{
		Graphics2D G2D = (Graphics2D) G;
		
		double xMod = 1;
		double yMod = 1;
		
		double nowX = 20;
		double nowY = 20;
		
		for(double i = 0; i < 360; i++)
		{
			G2D.setColor(Color.BLACK);
			G2D.drawLine((int) nowX, (int) nowY, (int) (nowX + i), (int) (nowY  + function(i) * yMod));
			
			//G2D.setColor(Color.RED);
			//G2D.drawLine((int) (nowX + function(i) * xMod), (int) (nowY + yMod), this.getWidth() - 20, (int) (nowY + yMod));
			//G2D.setColor(Color.GREEN);
			//G2D.drawLine((int) (nowX + function(i) * xMod), (int) (nowY + yMod), (int) (nowX + function(i) * xMod), this.getHeight() - 20);
			
			nowX = i;
			nowY += function(i) * yMod;
			
			System.out.println((nowX - 20) + " " + (nowY - 20));
		}
	}
	
	double function(double i) {return Math.sin(Math.toRadians(i));}
}