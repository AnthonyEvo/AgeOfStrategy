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
		
		System.out.println(Name + " " + type + " " + width + " " + heigth);
		
		for(int i = 0; i < 6; i++)
		{
			side[i] = new Side(width, heigth, i);
		}
	}
	
	synchronized public double[][] getPlanetaryGraphics()
	{
		int surfaceSquare = 0;
		
		int sideNum = 0;
		int regionXNum = 0;
		int regionYNum = 0;
		double buffer[][];
		
		for (int i = 0; i < side.length; i++)
		{
			surfaceSquare += side[i].region.length * side[i].region[0].length;
		}
		
		buffer = new double[surfaceSquare][3];
		
		for(int i = 0; i < buffer.length; i++)
		{
			if(regionXNum > side[sideNum].region.length - 1) 
			{
				regionXNum = 0;
				regionYNum++;
				//System.out.println();
			}
			
			if(regionYNum > side[sideNum].region[0].length - 1) 
			{
				regionYNum = 0;
				sideNum++;
				//System.out.println("Side #" + sideNum);
			}
			
			buffer[i][0] = this.side[sideNum].region[regionXNum][regionYNum].x;
			buffer[i][1] = this.side[sideNum].region[regionXNum][regionYNum].y;
			buffer[i][2] = this.side[sideNum].region[regionXNum][regionYNum].z;
			
			//System.out.print(buffer[i][0] + "|" + buffer[i][1] + "|" + buffer[i][2] + " ");
			
			regionXNum++;	
		}
		//System.out.println();
		
		return buffer;
	}
	
	void getChanges()
	{
		
	}
	
	void setChanges()
	{
		
	}
}

