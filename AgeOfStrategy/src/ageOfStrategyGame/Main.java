package ageOfStrategyGame;

import javax.swing.*;

import gameSourceLib.*;
import tankGame.*;
import experimental.*;

public class Main 
{
	public static void main(String args[])
	{
		GameObject tank = new GameObject("light_tank");
		tank.resetPosition();
		
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TankGameScreen screen = new TankGameScreen();
		frame.add(screen);
		frame.setVisible(true);
		
		screen.setData(tank.getLocate(false));		
		screen.reDraw();
		
//		TangentGraph tang = new TangentGraph();
//		WorldLoader example = new WorldLoader(498462135);
//		SwingUtilities.invokeLater(new Runnable() {public void run() {Core gameCore = new Core(1000, 720);}});
//		SwingUtilities.invokeLater(new Runnable() {public void run() {Example mainWindow = new Example();}});
	}
	
}