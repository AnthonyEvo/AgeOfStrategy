package tankGame;

public class ObjectCalculator 
{
	static double Buf[][];
	
	public static double[][] ObjectReBuild(double figure[][], int CursPos[])
	{
		Buf = new double[figure.length][5];
		double Angle = 0;	
		boolean Tower = false;
		int TowerPoint = 0;
		
		Buf[0][0] = figure[0][0] + Math.cos(Math.toRadians(figure[0][2])) * figure[0][3];
		Buf[0][1] = figure[0][1] + Math.sin(Math.toRadians(figure[0][2])) * figure[0][3];
	
		
		for(int i = 1; i < figure.length; i++)
		{
			if(!Tower)
			{
				double x = figure[i][0];
				double y = figure[i][1];
				double Rad = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			
				Angle = angReculc(x, y, Rad);
			
				Buf[i][0] = figure[0][0] + Math.cos(Math.toRadians((Angle + figure[0][2]) % 360)) * Rad;
				Buf[i][1] = figure[0][1] + Math.sin(Math.toRadians((Angle + figure[0][2]) % 360)) * Rad;
				Buf[i][4] = figure[i][4];
				
				if(figure[i][4] == 0) 
				{
					Tower = true;
					TowerPoint = i; 
				//	System.out.println(TowerPoint);
				}
				
				//System.out.println("Точка " + i + " стремится к координате х: " + Buf[i][0] + " y: " + Buf[i][1]);
			}
			else
			{
				double x = figure[i][0];
				double y = figure[i][1];
				double Rad = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
				
				double cursX = CursPos[0] - (figure[TowerPoint][0] + figure[0][0]);
				double cursY = CursPos[1] - (figure[TowerPoint][1] + figure[0][1]);
				double cursRad = Math.sqrt(Math.pow(cursX, 2) + Math.pow(cursY, 2));
				
				Angle = angReculc(x, y, Rad);
				double tAngle = angReculc(cursX, cursY, cursRad);
				
				Buf[i][0] = Buf[TowerPoint][0] + Math.cos(Math.toRadians((Angle + tAngle) % 360)) * Rad;	//Записывает положение точки по оси Х относительно якоря
				Buf[i][1] = Buf[TowerPoint][1] + Math.sin(Math.toRadians((Angle + tAngle) % 360)) * Rad;	//Записывает положение точки по оси У относительно якоря
				Buf[i][4] = figure[i][4];
				// System.out.println("Ширина от башни до курсора: " + cursX + " высота от башни до курсора " + cursY);
				//System.out.println("Точка " + i + " стремится к координате х: " + Buf[i][0] + " y: " + Buf[i][1]);
			}
			
		}
		return Buf;
	}
	
	public static double[][] ObjectReBuild(double TrajPoint[][], String type)
	{
		//System.out.print("Fire!!!");
		double figure[][] = ObjectLoader.Load("Bullet",(int) TrajPoint[0][0],(int) TrajPoint[0][1]);
		double Angle = 0;
		
		double trX = TrajPoint[0][0] - TrajPoint[1][0];	//задает положение по оси Х относительно якоря.
		double trY = TrajPoint[0][1] - TrajPoint[1][1];	//задает положение по оси У относительно якоря.
		double trRad = Math.sqrt(Math.pow(trX, 2) + Math.pow(trY, 2));	//Расчет расстояния точки от основного якоря
		
		figure[0][2] = angReculc(trX, trY, trRad);
		
		Buf[0][0] = TrajPoint[0][0] + Math.cos(Math.toRadians(figure[0][2] % 360)) * TrajPoint[0][3];	//Записывает положение точки по оси Х относительно якоря
		Buf[0][1] = TrajPoint[0][1] + Math.sin(Math.toRadians(figure[0][2] % 360)) * TrajPoint[0][3];	//Записывает положение точки по оси У относительно якоря
		Buf[0][4] = TrajPoint[0][4];
		
		for(int i = 1; i < figure.length; i++)
		{
			double x = figure[i][0];	//задает положение по оси Х относительно якоря.
			double y = figure[i][1];	//задает положение по оси У относительно якоря.
			double Rad = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));	//Расчет расстояния точки от основного якоря
		
			Angle = angReculc(x, y, Rad);
		
			Buf[i][0] = Buf[0][0] + Math.cos(Math.toRadians((Angle + figure[0][2]) % 360)) * Rad;	//Записывает положение точки по оси Х относительно якоря
			Buf[i][1] = Buf[0][1] + Math.sin(Math.toRadians((Angle + figure[0][2]) % 360)) * Rad;	//Записывает положение точки по оси У относительно якоря
			Buf[i][4] = figure[i][4];
		}
		return Buf;
	}
	
	private static double angReculc(double x, double y, double Rad)
	{
		double Angle = 0;
		if(x > 0 && y > 0) Angle = (Math.toDegrees(Math.asin(y / Rad)));	//Получает угол для пересчета положения
		if(x > 0 && y < 0) Angle = (360 + Math.toDegrees(Math.asin(y / Rad)));	//в координатной плоскости
		if(x < 0 && y > 0) Angle = (Math.toDegrees(Math.asin(y / Rad)) + (90 - Math.toDegrees(Math.asin(y / Rad))) * 2);	//для последующего перерасчета
		if(x < 0 && y < 0) Angle = (Math.toDegrees(Math.asin(y / Rad)) - (90 + Math.toDegrees(Math.asin(y / Rad))) * 2);	//положения точки с учетом угла разворота 
		if(x == 0 && y > 0) Angle = 90;	//объекта.
		if(x == 0 && y < 0) Angle = 270;
		if(x > 0 && y == 0) Angle = 0;
		if(x < 0 && y == 0) Angle = 180;
		
		return Angle;
	}
}