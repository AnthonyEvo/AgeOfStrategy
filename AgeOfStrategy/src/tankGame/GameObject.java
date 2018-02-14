package tankGame;

import java.util.ArrayList;

public class GameObject 
{
	int posZ, globalAngle = 50, pointSum = 0;
	double posX = 300, posY = 200;
	String objectType;
	
	ArrayList<Part> parts = new ArrayList<Part>(0);
	
	public GameObject(String objectType)
	{
		this.objectType = objectType;
		loadModel();
	}
	
	void loadModel()
	{
		ModelLoader model = new ModelLoader(objectType);
		
		for(int i = 0; i < model.getNumOfParts(); i++)
		{
			parts.add(new Part(model, i));
			pointSum += parts.get(i).getPointsNum();
		}
	}
	
	public void resetPosition()
	{
		parts.get(1).setLocalAngle(37); 
	} 
	
	public double[][][] getLocate(boolean isKeyPosition)
	{
		double arr[][][];
		
		if(!isKeyPosition)
		{	
			arr = new double[parts.size()][][];
			
			for(int i = 0; i < parts.size() && parts.get(i) != null; i++)
			{
				arr[i] = parts.get(i).getPosition();
			}
			return arr;
		}
		else
		{
			arr = new double[1][1][3];
			arr[0][0][0] = posX;
			arr[0][0][1] = posY;
			arr[0][0][2] = globalAngle;
			return arr;
		}
	}
	
	class Part
	{
		final double partKeyPoint[], partPoints[][];
		int localAngle = 0, baseAngle, angleNow;
		int motherPartNum;
		
		Part(ModelLoader model, int partNum)
		{
			partKeyPoint = model.getKeyPoints(partNum);
			partPoints = model.getPoints(partNum);
			motherPartNum = model.getDependency(partNum);
			angleNow = getMotherPartAngle() + localAngle;
		}
		
		int getMotherPartAngle()
		{
			if(motherPartNum != 0)return parts.get(motherPartNum).getLocalAngle();
			else return globalAngle;
		}
		
		int getLocalAngle()
		{
			return localAngle;
		}
		
		void setLocalAngle(int angle)
		{
			localAngle = angle;
			angleNow = getMotherPartAngle() + localAngle;
		}
		
		int getPointsNum()
		{
			return partPoints.length;
		}
		
		double[][] getPosition()
		{
			double Rad = Math.sqrt(Math.pow(partKeyPoint[0], 2) + Math.pow(partKeyPoint[1], 2));;
			double arr[][] = new double[partPoints.length][2];
			double keyPointNowX = posX + Math.cos(Math.toRadians((partKeyPoint[2] + angleNow) % 360)) * Rad;
			double keyPointNowY = posY + Math.sin(Math.toRadians((partKeyPoint[2] + angleNow) % 360)) * Rad;
			
			for(int i = 0; i < arr.length; i++)
			{
				Rad = Math.sqrt(Math.pow(partPoints[i][0], 2) + Math.pow(partPoints[i][1], 2));
				arr[i][0] = keyPointNowX + Math.cos(Math.toRadians((partPoints[i][2] + angleNow) % 360)) * Rad;
				arr[i][1] = keyPointNowY + Math.sin(Math.toRadians((partPoints[i][2] + angleNow) % 360)) * Rad;
//				System.out.println(arr[i][0] + " " + arr[i][1]);
			}
			return arr;
		}
	}
}