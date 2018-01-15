package ageOfStrategyGame;

import java.awt.*;

import javax.swing.*;

public class SidePanel extends JPanel 
{
	JButton spButton1;
	JButton spButton2;
	JButton spButton3;
	
	SidePanel()
	{
		this.setLayout(new FlowLayout());
		System.out.println("Sidebar created");
		
		spButton1 = new JButton("Start Tank Game");
		this.add(spButton1);
		
		spButton2 = new JButton("Start Age os Strategy");
		this.add(spButton2);
		
		spButton3 = new JButton("Back to main menu");
		this.add(spButton3);
		
	}
	
	public void printSize()
	{
		System.out.println("Camera x:" + this.getWidth() + " y:" + this.getHeight());
	}
}
