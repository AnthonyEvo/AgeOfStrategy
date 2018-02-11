package tankGame;

public class ModelLoader 
{
	int keyPoint[], points[][];
	String modelType;
	
	public ModelLoader(String modelType)
	{
		this.modelType = modelType;
	}
	
	public double[] getKeyPoints(int partNum)
	{
		boolean isKey = true;
		return load(modelType, partNum, isKey)[0];	
	}
	
	public double[][] getPoints(int partNum)
	{
		boolean isKey = false;
		return load(modelType, partNum, isKey);
	}
	
	public int getNumOfParts()
	{
		if(modelType == "light_tank") return 1;
		else return 0;
	}
	
	double angReculc(double x, double y)
	{
		double Angle = 0;
		double Rad = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		
		if(x > 0 && y > 0) Angle = (Math.toDegrees(Math.asin(y / Rad)));
		if(x > 0 && y < 0) Angle = (360 + Math.toDegrees(Math.asin(y / Rad)));
		if(x < 0 && y > 0) Angle = (Math.toDegrees(Math.asin(y / Rad)) + (90 - Math.toDegrees(Math.asin(y / Rad))) * 2);
		if(x < 0 && y < 0) Angle = (Math.toDegrees(Math.asin(y / Rad)) - (90 + Math.toDegrees(Math.asin(y / Rad))) * 2);
		if(x == 0 && y > 0) Angle = 90;
		if(x == 0 && y < 0) Angle = 270;
		if(x > 0 && y == 0) Angle = 0;
		if(x < 0 && y == 0) Angle = 180;
		
		return Angle;
	}
	
	double[][] load(String modelType, int partNum, boolean isKey)
	{
		switch(partNum)
		{
			case 0:
			{
				double arr[][];
				if(isKey)
				{
					arr = new double[1][3];
					arr[0][0] = 0;
					arr[0][1] = 0;
					arr[0][2] = 0;
				}
				else
				{
					arr = new double[4][3];
					arr[0][0] = 20;
					arr[0][1] = 10;
					arr[0][2] = angReculc(arr[0][0], arr[0][1]);
				
					arr[1][0] = -20;
					arr[1][1] = 10;
					arr[1][2] = angReculc(arr[1][0], arr[1][1]);
				
					arr[2][0] = -20;
					arr[2][1] = -10;
					arr[2][2] = angReculc(arr[2][0], arr[2][1]);
				
					arr[3][0] = 20;
					arr[3][1] = -10;
					arr[3][2] = angReculc(arr[3][0], arr[3][1]);
				}
				return arr;
			}	
			case 1:
			{	
				double arr[][];
				if(isKey)
				{
					arr = new double[1][3];
					arr[0][0] = 0;
					arr[0][1] = -5;
					arr[0][2] = angReculc(arr[0][0], arr[0][1]);
				}
				else
				{
					arr = new double[5][3];
					arr[0][0] = 8;
					arr[0][1] = 8;
					arr[0][2] = angReculc(arr[0][0], arr[0][1]);
					
					arr[1][0] = -8;
					arr[1][1] = 8;
					arr[1][2] = angReculc(arr[1][0], arr[1][1]);
					
					arr[2][0] = -8;
					arr[2][1] = -8;
					arr[2][2] = angReculc(arr[2][0], arr[2][1]);
					
					arr[3][0] = 8;
					arr[3][1] = -8;
					arr[3][2] = angReculc(arr[3][0], arr[3][1]);
					
					arr[4][0] = 16;
					arr[4][1] = 0;
					arr[4][2] = angReculc(arr[4][0], arr[4][1]);
				}
				return arr;
			}
			default:
				return null;
		}
	}
	
	class Part
	{
		
	}
}
