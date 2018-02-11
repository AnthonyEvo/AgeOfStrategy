package tankGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Vehicle 
{

	int posZ, globalAngle = 34, localPart1Angle = 71;
	double posX = 300, posY = 300;
	String vehicleType;
	TankControl control;
	MainPart body;
	
	ArrayList<DependentPart> parts = new ArrayList<DependentPart>(0);
	
	public Vehicle(String vehicleType)
	{
		this.vehicleType = vehicleType;
//		this.control = control;
		loadModel();
		
		//проверка графики
		
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
	}
	
	void loadModel()
	{
		ModelLoader model = new ModelLoader(vehicleType);
		body = new MainPart(model);
		for(int i = 0; i < model.getNumOfParts(); i++)
		{
			parts.add(new DependentPart(model, i + 1));
		}
	}
	
	void resetPosition()
	{
		
	} 
	
	public double[][] getPositionData() 
	{
		body.getPosition();
		for(int i = 0; i < parts.size() && parts.get(i) != null; i++)
		{
			parts.get(i).getPosition();
		}
		return null;
	}
	
	class DependentPart
	{
		final double dPartKeyPoint[], dPartPoints[][];
		boolean isTower = true;
		
		DependentPart(ModelLoader model, int partNum)
		{
			dPartKeyPoint = model.getKeyPoints(partNum);
			dPartPoints = model.getPoints(partNum);
		}
		
		double[][] getPosition()
		{
			double Rad = Math.sqrt(Math.pow(dPartKeyPoint[0], 2) + Math.pow(dPartKeyPoint[1], 2));
			double arr[][] = new double[dPartPoints.length][2];
			double keyPointNowX = posX + Math.cos(Math.toRadians((dPartKeyPoint[2] + globalAngle) % 360)) * Rad;
			double keyPointNowY = posY + Math.sin(Math.toRadians((dPartKeyPoint[2] + globalAngle) % 360)) * Rad;
			
			for(int i = 0; i < arr.length; i++)
			{
				Rad = Math.sqrt(Math.pow(dPartPoints[i][0], 2) + Math.pow(dPartPoints[i][1], 2));
				arr[i][0] = keyPointNowX + Math.cos(Math.toRadians((dPartPoints[i][2] + globalAngle + localPart1Angle) % 360)) * Rad;
				arr[i][1] = keyPointNowY + Math.sin(Math.toRadians((dPartPoints[i][2] + globalAngle + localPart1Angle) % 360)) * Rad;
				System.out.println(arr[i][0] + " " + arr[i][1]);
			}
			
			return arr;
		}
	}

	class MainPart
	{
		final double mPartKeyPoint[], mPartPoints[][];
		boolean isTower = false;
		
		MainPart(ModelLoader model)
		{
			mPartKeyPoint = model.getKeyPoints(0);
			mPartPoints = model.getPoints(0);
		}
		
		double[][] getPosition()
		{
			double Rad;
			double arr[][] = new double[mPartPoints.length][2];
			double keyPointNowX = posX;
			double keyPointNowY = posY;
			
			for(int i = 0; i < arr.length; i++)
			{
				Rad = Math.sqrt(Math.pow(mPartPoints[i][0], 2) + Math.pow(mPartPoints[i][1], 2));
				arr[i][0] = keyPointNowX + Math.cos(Math.toRadians((mPartPoints[i][2] + globalAngle) % 360)) * Rad;
				arr[i][1] = keyPointNowY + Math.sin(Math.toRadians((mPartPoints[i][2] + globalAngle) % 360)) * Rad;
				System.out.println(arr[i][0] + " " + arr[i][1]);
			}
			return arr;
		}
	}	
}