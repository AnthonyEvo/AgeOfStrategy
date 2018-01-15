package gameSourceLib;

public class Side {
	
	int width;
	int height;
	
	public Region[][] region;
	
	Side(int width, int height, int direction)
	{
		this.width = width;
		this.height = height;
		setRegionCoords(direction);
	}
	
	void initSide(int posWidth, int posHeight, char cBlock, boolean isNegative)
	{
		double modWidth = ((width + 1) % 2) / 2.0;
		double modHeight = ((height + 1) % 2) / 2.0;
		
		region = new Region[posWidth][posHeight];
		
		System.out.print("Side direction: ");
		if(!isNegative) System.out.print(cBlock + "\n\n");
		else System.out.print("-" + cBlock + "\n\n");
		
		for(int i = 0; i < posWidth; i++)
		{
			for(int j = 0; j < posHeight; j++)
			{
				region[i][j] = new Region();
				
				if(cBlock == 'x')
				{
					region[i][j].y = i - (posWidth / 2) + modWidth;
					region[i][j].z = j - (posHeight / 2) + modHeight;
					
					if(isNegative)	region[i][j].x = -width / 2 + modWidth;
					else region[i][j].x = width / 2 - modWidth;
				}
				
				if(cBlock == 'y')
				{
					region[i][j].x = i - (posWidth / 2) + modWidth;
					region[i][j].z = j - (posHeight / 2) + modHeight;
					
					if(isNegative)	region[i][j].y = -width / 2 + modWidth;
					else region[i][j].y = width / 2 - modWidth;
				}
				
				if(cBlock == 'z')
				{
					region[i][j].x = i - (posWidth / 2) + modWidth;
					region[i][j].y = j - (posHeight / 2) - modHeight;
					
					if(isNegative)	region[i][j].z = -height / 2 + modHeight;
					else region[i][j].z = height / 2 - modHeight;
				}
				
				System.out.print("(" + region[i][j].x + "|" + region[i][j].y + "|" + region[i][j].z + ") ");
			}
			
			System.out.println("");
			
		}	
	}
	
	void setRegionCoords(int direction)
	{
		char cordDirection;
		
		switch (direction)
		{
			case 0:
				cordDirection = 'z';
				initSide(width, width, 'z', false);
				break;
				
			case 1:
				cordDirection = 'y';
				initSide(width, height, 'y', false);
				break;
				
			case 2:
				cordDirection = 'x';
				initSide(width, height, 'x', false);
				break;
				
			case 3:
				cordDirection = 'y';
				initSide(width, height, 'y', true);
				break;
				
			case 4:
				cordDirection = 'x';
				initSide(width, height, 'x', true);
				break;
				
			case 5:
				cordDirection = 'z';
				initSide(width, width, 'z', true);
				break;
		}
	}
}
