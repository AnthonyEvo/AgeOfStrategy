package tankGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Example extends JFrame implements KeyListener
{
	private static final long serialVersionUID = 1L;
	
	static int width = 500, height = 500, Turbo = 1;
	static boolean Forw = false, Back = false, TRight = false, TLeft = false;
	JLabel Lab = new JLabel("State");
	
	public Example()
	{
		FrameInit();
	}
	
	private void FrameInit()
	{
		final TankGameScreen grp = new TankGameScreen();
		add(grp);
		
		grp.setPreferredSize(new Dimension( width - 20, height - 20));
		addKeyListener(this);
		
		setSize(width + 8, height + 16);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Танк");
		setLayout(new FlowLayout());
		setVisible(true);
	}
	
//--KeyboardListener--
	
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
	
	public void keyTyped(KeyEvent ke)
	{
		
	}
	
}