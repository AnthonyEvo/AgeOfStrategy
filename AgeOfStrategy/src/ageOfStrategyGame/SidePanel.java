package ageOfStrategyGame;

import java.awt.*;
import javax.swing.*;

public class SidePanel extends JPanel 
{
	JButton spButton1;
	JButton spButton2;
	JButton spButton3;
	JButton spButton4;
	
	SidePanel()
	{
		this.setLayout(new GridLayout(20, 1, 1, 1));
		System.out.println("Sidebar created");
		
		spButton1 = new JButton("Start Tank Game");
		this.add(spButton1);
		
		spButton2 = new JButton("Start Age of Strategy");
		//this.add(spButton2);
		
		spButton3 = new JButton("Generate world");
		//this.add(spButton3);
		
		spButton4 = new JButton("Back to main menu");
		this.add(spButton4);
	}
	
	public void printSize()
	{
		System.out.println("Camera x:" + this.getWidth() + " y:" + this.getHeight());
	}
}
