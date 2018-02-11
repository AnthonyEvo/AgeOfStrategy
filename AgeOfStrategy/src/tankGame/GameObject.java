package tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;

public class GameObject 
{

	int posZ, globalAngle = 34;
	double posX = 300, posY = 300;
	String vehicleType;
	
	ArrayList<Part> parts = new ArrayList<Part>(0);
	
	public GameObject(String vehicleType)
	{
		this.vehicleType = vehicleType;
		loadModel();
		
		//проверка графики
		
		
	}
	
	void loadModel()
	{
		ModelLoader model = new ModelLoader(vehicleType);
		
		for(int i = 0; i < model.getNumOfParts(); i++)
		{
			parts.add(new Part(model, i));
		}
	}
	
	void resetPosition()
	{
		
	} 
	
	public double[][] getPositionData(boolean isKey) 
	{
		for(int i = 0; i < parts.size() && parts.get(i) != null; i++)
		{
			parts.get(i).getPosition();
		}
		return null;
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
				System.out.println(arr[i][0] + " " + arr[i][1]);
			}
			return arr;
		}
	}
}