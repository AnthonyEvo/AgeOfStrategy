package tankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TankGameScreen extends JPanel implements Runnable, MouseMotionListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	
	private Graphics2D g2D;
	double Figure[][];
	double rFigure[][];
	double Bullet[][] = new double[2][5];
	
	int cursorCoor[] = new int[] {0, 0};
	boolean Lounch = false;
	
	static int Turbo = 1;
	static boolean Forw = false, Back = false, TRight = false, TLeft = false;
	
	public TankGameScreen()
	{
		addMouseMotionListener(this);
		addMouseListener(this);
		
		Thread thr = new Thread(this, "timer");
		thr.start();
	}
	
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		draw(G);
	}
	
	private void draw(Graphics G)
	{
		g2D = (Graphics2D) G;
		g2D.setColor(Color.BLACK);
		
		for(int i = 1; i < Figure.length; i++)
		{
			if((int) rFigure[i][4] != 0)
			g2D.drawLine((int) rFigure[i][0], (int) rFigure[i][1], (int) rFigure[(int) rFigure[i][4]][0], (int) rFigure[(int) rFigure[i][4]][1]);
		}	
		g2D.drawString("W - вперед, S - назад, A - влево, D - вправо", 5, this.getHeight() - 40);
		g2D.drawString("X: " + cursorCoor[0] + " Y: " + cursorCoor[1], 5, this.getHeight() - 25);
	}
	
	private void reCalc()
	{	
		rFigure = ObjectCalculator.ObjectReBuild(Figure, cursorCoor);
		Figure[0][0] = rFigure[0][0];
		Figure[0][1] = rFigure[0][1];
	}
	
	public void run()
	{	
//		Figure = ObjectLoader.Load("Tank", 50, 50);
//		rFigure = new double[(int) Figure[0][4]][5];
		
		while(true)
		{			
			try 
			{
				if(Example.Forw)
				{
					Figure[0][3] = 2 * Example.Turbo;
				}
				else if(Example.Back)
				{
					Figure[0][3] = -2 * Example.Turbo;
				}
				else
				{
					Figure[0][3] = 0;
				}
				
				if(Example.TRight == true)
				{
					Figure[0][2] -= 2;
				}
					
				if(Example.TLeft == true)
				{
					Figure[0][2] += 2;
				}
				if(Lounch)
				{
					
				}
				
				Thread.sleep(25);
			}
			catch (InterruptedException e)
			{
				
			}
			
			reCalc();
			repaint();
		}
	}	
	
	//--Mouse listener--
	
	public void mouseClicked(MouseEvent m) 
	{
		
		for(int i = 1; i < rFigure.length; i++)
		{
			if(rFigure[i][4] == 0) 
			{
				Bullet[0][0] = rFigure[i][0];
				Bullet[0][1] = rFigure[i][1];
			}
		}
		
		Bullet[1][0] = getX();
		Bullet[1][1] = getY();
		Bullet[1][3] = 8;
		
		Lounch = true;
	}
	
	public void mouseEntered(MouseEvent mE) 
	{
		
	}
	
	public void mouseExited(MouseEvent arg0)
	{
		
	}
	
	public void mousePressed(MouseEvent mE) 
	{
		
	}
	
	public void mouseReleased(MouseEvent arg0) 		
	{
			
	}

	public void mouseDragged(MouseEvent arg0) 
	{
			
	}

	public void mouseMoved(MouseEvent mE) 
	{
		cursorCoor[0] = mE.getX();
		cursorCoor[1] = mE.getY();
	}	
	
	synchronized public void keyPressed(KeyEvent ke)
	{
		switch(ke.getKeyCode())
		{
			case 87:
				Forw = true;
				break;
			case 83:
				Back = true;
				break;
			case 65:
				TRight = true;
				break;
			case 68:
				TLeft = true;
				break;
			case 32:
				Turbo = 2;
				break;
		}
	}
	
	synchronized public void keyReleased(KeyEvent ke)
	{
		switch(ke.getKeyCode())
		{
			case 87:
				Forw = false;
				break;
			case 83:
				Back = false;
				break;
			case 68:
				TLeft = false;
				break;
			case 65:
				TRight = false;
				break;
			case 32:
				Turbo = 1;
				break;
		}
	}

	public void keyTyped(KeyEvent arg0) 
	{
		
	}
}
