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
	boolean formLoaded = false;
	
	public TankGameScreen()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		draw(G);
		
	}
	
	public void draw(Graphics G)
	{
		G.setColor(Color.BLACK);
		if(formLoaded) {
			for(double i[][]: form) {
				G.drawLine((int) i[i.length - 1][0], (int) i[i.length - 1][1], (int) i[0][0], (int) i[0][1]);
				for(int j = 1; j < i.length; j++)
				{
					G.drawLine((int) i[j - 1][0], (int) i[j - 1][1], (int) i[j][0], (int) i[j][1]);
				}
			}
			System.out.println("Drawed");
			formLoaded = false;
		}
	}
	
	public void setData(double[][][] _form)
	{
		double buf[][][] = new double[_form.length][][];
		int formCount = 0;
		
		if(formLoaded) {
			formCount = form.length;
			buf = new double[form.length + _form.length][][];
			for(int i = 0 ; i < form.length; i++) {
				buf[i] = new double[form[i].length][];
				for(int j = 0; j < form[i].length; j++) {
					buf[i][j] = new double[form[i][j].length];
					for(int k = 0; k < form[i][j].length; k++) {
						buf[i][j][k] = form[i][j][k];
					}
				}
			}
		}
				
		for(int i = 0 ; i < _form.length; i++) {
			buf[i + formCount] = new double[_form[i].length][];
			for(int j = 0; j < _form[i].length; j++) {
				buf[i + formCount][j] = new double[_form[i][j].length];
				for(int k = 0; k < _form[i][j].length; k++) {
					buf[i  + formCount][j][k] = _form[i][j][k];
				}
			}
		}
		
		formLoaded = true;
		form = buf;
	}
	
	public void reDraw()
	{
		repaint();
	}
}
