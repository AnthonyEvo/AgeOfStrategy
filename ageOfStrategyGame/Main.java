package ageOfStrategyGame;

import javax.swing.*;

import gameSourceLib.*;
import tankGame.*;

public class Main 
{
	public static void main(String args[])
	{
		//WorldLoader example = new WorldLoader(498462135);
		SwingUtilities.invokeLater(new Runnable() {public void run() {Core gameCore = new Core(1000, 720);}});
		//SwingUtilities.invokeLater(new Runnable() {public void run() {Example mainWindow = new Example();}});
	}
	
}