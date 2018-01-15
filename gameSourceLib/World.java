package gameSourceLib;

public class World 
{
	String worldName;
	String type;
	int width, height;
	
	Side[] side = new Side[6];
	
	World(String Name, String type, int width, int heigth)
	{
		this.worldName = Name;
		this.type = type;
		this.width = width;
		this.height = heigth;
		
		for(int i = 0; i < 6; i++)
		{
			side[i] = new Side(width, heigth, i);
		}
		
		System.out.println(Name + " " + type + " " + width + " " + heigth);
	}
	
	synchronized public double[][] getPlanetaryGraphics()
	{
		double buffer[][] = new double[width * height * 6][3];
		int sideNum = 0;
		int regionXNum = 0;
		int regionYNum = 0;
		
		for(int i = 0; i < buffer.length; i++)
		{
			buffer[i][0] = this.side[sideNum].region[regionXNum][regionYNum].x;
			buffer[i][1] = this.side[sideNum].region[regionXNum][regionYNum].y;
			buffer[i][2] = this.side[sideNum].region[regionXNum][regionYNum].z;
			
			regionXNum++;
			
			if(i % width > width) 
			{
				regionXNum = 0;
				regionYNum++;
			}
			
			if(i % height > height) 
			{
				regionYNum = 0;
				sideNum++;
			}
			
			regionXNum++;
		}
		
		return buffer;
	}
	
	void getChanges()
	{
		
	}
	
	void setChainges()
	{
		
	}
}

