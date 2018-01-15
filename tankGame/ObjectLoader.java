package tankGame;

import java.io.*;

public class ObjectLoader 
{
	ObjectLoader()
	{
		int i = 0;
		int count = 0;
		int Buf[][] = new int[1][6];
		try(FileInputStream s = new FileInputStream("C:\\Users\\User\\Desktop\\workspace\\Trainig\\src\\TrainingPack\\Untitled.txt"))
		{
			while(i != -1)
			{
				int inCount = 0;
				i = s.read();
				
				if(i >= 48 && i <= 57)
				{
					Buf[count][inCount] = i - 48;
					inCount++;
				}
				if(i == 44)
				{
					count++;
					inCount = 0;
				}
				System.out.print(Buf[0][0]);
			}
		}
		catch(IOException Ex)
		{
			System.out.print(Ex);
		}
	}
	
	public static double[][] Load(String Object, int x, int y)
	{
		double Arr[][];
		int points;
		switch(Object)
		{
			case "Tank":
				points = 11;
				Arr = new double[points][5];
				
				Arr[0][0] = x;
				Arr[0][1] = y;
				Arr[0][4] = points;
				
				Arr[1][0] = 20;
				Arr[1][1] = 10;
				Arr[1][4] = 2;
				
				Arr[2][0] = -20;
				Arr[2][1] = 10;
				Arr[2][4] = 3;
				
				Arr[3][0] = -20;
				Arr[3][1] = -10;
				Arr[3][4] = 4;
				
				Arr[4][0] = 20;
				Arr[4][1] = -10;
				Arr[4][4] = 1;
				
				Arr[5][0] = -5;
				Arr[5][1] = 0;
				Arr[5][4] = 0;
				
				Arr[6][0] = 8;
				Arr[6][1] = 8;
				Arr[6][4] = 7;
				
				Arr[7][0] = -8;
				Arr[7][1] = 8;
				Arr[7][4] = 8;
				
				Arr[8][0] = -8;
				Arr[8][1] = -8;
				Arr[8][4] = 9;
				
				Arr[9][0] = 8;
				Arr[9][1] = -8;
				Arr[9][4] = 10;
				
				Arr[10][0] = 16;
				Arr[10][1] = 0;
				Arr[10][4] = 6;
				break;
				
			case "Bullet":
				points = 6;
				Arr = new double[points][5];
				
				Arr[0][0] = x;
				Arr[0][1] = y;
				Arr[0][4] = points;
				
				Arr[1][0] = 2;
				Arr[1][1] = 1;
				Arr[1][4] = 2;
				
				Arr[2][0] = -2;
				Arr[2][1] = 1;
				Arr[2][4] = 3;
				
				Arr[3][0] = -2;
				Arr[3][1] = -1;
				Arr[3][4] = 4;
				
				Arr[4][0] = 3;
				Arr[4][1] = 0;
				Arr[4][4] = 5;
				
				Arr[5][0] = 2;
				Arr[5][1] = -1;
				Arr[5][4] = 1;
				
				return Arr;
				
			default:
				Arr = new double[1][1];
				break;
		}
		return Arr;
	}
	
	public int LoadCount()
	{
		return 0;
	}
}
