package tankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TankGameScreen extends JPanel
{
	double[][][] form;
	
	public TankGameScreen()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		if(form != null)draw(G);
	}
	
	public void draw(Graphics G)
	{
		G.setColor(Color.BLACK);
		
		for(double i[][]: form)
		{
			G.drawLine((int) i[i.length - 1][0], (int) i[i.length - 1][1], (int) i[0][0], (int) i[0][1]);
			for(int j = 1; j < i.length; j++)
			{
				G.drawLine((int) i[j - 1][0], (int) i[j - 1][1], (int) i[j][0], (int) i[j][1]);
			}
		}
	}
	
	public void setData(double[][][] form)
	{
		this.form = form;
	}
	
	public void reDraw()
	{
		repaint();
	}
}
