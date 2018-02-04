package Aditional;

import java.util.Random;

public class GameEngine implements Runnable
{
	GameSpace GameS;
	
	EventPool InputPool;
	EventPool OutputPool;
	EventPool GamePool;
	
	GameEngine(EventPool InputPool, EventPool OutputPool)
	{
		this.InputPool = InputPool;
		this.OutputPool = OutputPool;
		
		Thread GEngine = new Thread(this, "GameEngine");
		GEngine.start();
	}
	
	public void run()
	{
		while(true)
		{
			
		}
	}
	
	synchronized public void endLoop()
	{
		
	}
	
	public void setOutput()
	{
		
	}
	
	public void calculateActiveGameSpace()
	{
		
	}
}

class GameSpace
{
	int LocationRadius = 1000 + new Random().nextInt() % 50;
	
	Star Sun = new Star();
	
	GameSpace()
	{
		
	}
	
	void recalculateAllObjects()
	{
		
	}
}

