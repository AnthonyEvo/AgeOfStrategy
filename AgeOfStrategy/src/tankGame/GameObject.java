package tankGame;

import java.util.ArrayList;

/* Это у нас любой объект на игровой карте.
 *
 * У любого объекта должна быть координата - местоположение. 
 * Должна быть модель, а точнее имя.
 * Он должен быть материальным или нематериальным.
 * Он должен состоять как минимум из одной части! 
 * У объекта должен быть буфер команд контроллеров
 * и метод обрабатывающий их!
 */

public class GameObject {
	
	int posZ, pointSum = 0; // Высота, угол разворота, количество вершин модели
	double posX, posY, globalAngle = 50; // Координаты в пространстве
	final double maxLinearSpeed = 4.0, 
			maxAngularSpeed = 7.0, 
			maxLinearAcceleration = 0.01, 
			maxAngularAcceleration = 0.8;
	
	double nowLinearSpeed = 0, 
			nowAngularSpeed = 0;
	
	String objectType; //Название того что это такое
	String actualCommands[] = new String[5];
	
	ArrayList<Part> parts = new ArrayList<Part>(0); // Части из которых это что-то состоит
	
	public GameObject(String objectType, double posX, double posY) {
		this.objectType = objectType;
		this.posX = posX;
		this.posY = posY;
		loadModel();
	}
	
	void loadModel() {
		// Загружаем модельку по названию
		ModelLoader model = new ModelLoader(objectType);
//		System.out.println(objectType);
		// Тут заполняем детальки из которых модель состоит
		for(int i = 0; i < model.getNumOfParts(); i++) {
			parts.add(new Part(model, i));
			pointSum += parts.get(i).getPointsNum();
//			System.out.print(pointSum);
		}
	}
	
	public void resetPosition() {
		parts.get(0).setLocalAngle(0); 
		parts.get(1).setLocalAngle(37);
	} 
	
	public double[][][] getLocate(boolean isKeyPosition) {
		double arr[][][];
		
		if(!isKeyPosition) {	
			arr = new double[parts.size()][][];
			
			for(int i = 0; i < parts.size() && parts.get(i) != null; i++) {
				arr[i] = parts.get(i).getPosition();
			}
			return arr;
		} else {
			arr = new double[1][1][3];
			arr[0][0][0] = posX;
			arr[0][0][1] = posY;
			arr[0][0][2] = globalAngle;
			return arr;
		}
	}
	
	void move(double maxSpeed) {
		if(nowLinearSpeed + maxLinearAcceleration < maxLinearSpeed && nowLinearSpeed + maxLinearAcceleration < maxSpeed) {
			nowLinearSpeed += maxLinearAcceleration;
		}
		posX += Math.cos(Math.toRadians(globalAngle % 360)) * nowLinearSpeed;
		posY += Math.sin(Math.toRadians(globalAngle % 360)) * nowLinearSpeed;
		System.out.println("Moving to: " + (int) posX + "," + (int) posY);
	}
	
	void rotate(double maxSpeed) {
		if(maxSpeed < maxAngularSpeed) {
			nowAngularSpeed = maxSpeed;
		} else {
			nowAngularSpeed = maxAngularSpeed;
		}
		globalAngle += nowAngularSpeed;
		globalAngle %= 360;
		System.out.print("Rotating to: " + globalAngle + "\n");
	}
	
	public void setCommand(String command) {
		int i = 0;
		while(actualCommands[i] != null) {
			i++;
		}
		actualCommands[i] = command;
	}
	
	void handleCommands() {
		int i = 0;
		while(actualCommands[i] != null) {
			
			switch(actualCommands[i]) {
			case "move":
				move(4);
				break;
			case "rotate":
				rotate(2);
				break;
			case "stop":
				actualCommands = new String[5];
				break;
			default:
				break;
			}
			
			i++;
		}
		resetPosition();
	}
	
	class Part {
		final double partKeyPoint[], partPoints[][];
		double localAngle = 0, baseAngle, angleNow;
		int motherPartNum;
		
		Part(ModelLoader model, int partNum) {
			partKeyPoint = model.getKeyPoints(partNum);
			partPoints = model.getPoints(partNum);
			motherPartNum = model.getDependency(partNum);
			angleNow = getMotherPartAngle() + localAngle;
		}
		
		double getMotherPartAngle() {
			if(motherPartNum != 0)return parts.get(motherPartNum).getLocalAngle();
			else return globalAngle;
		}
		
		double getLocalAngle() {
			return localAngle;
		}
		
		void setLocalAngle(int angle) {
			localAngle = angle;
			angleNow = getMotherPartAngle() + localAngle;
		}
		
		int getPointsNum() {
			return partPoints.length;
		}
		
		double[][] getPosition() {
			double Rad = Math.sqrt(Math.pow(partKeyPoint[0], 2) + Math.pow(partKeyPoint[1], 2));;
			double arr[][] = new double[partPoints.length][2];
			double keyPointNowX = posX + Math.cos(Math.toRadians((partKeyPoint[2] + angleNow) % 360)) * Rad;
			double keyPointNowY = posY + Math.sin(Math.toRadians((partKeyPoint[2] + angleNow) % 360)) * Rad;
			
			for(int i = 0; i < arr.length; i++) {
				Rad = Math.sqrt(Math.pow(partPoints[i][0], 2) + Math.pow(partPoints[i][1], 2));
				arr[i][0] = keyPointNowX + Math.cos(Math.toRadians((partPoints[i][2] + angleNow) % 360)) * Rad;
				arr[i][1] = keyPointNowY + Math.sin(Math.toRadians((partPoints[i][2] + angleNow) % 360)) * Rad;
//				System.out.println(arr[i][0] + " " + arr[i][1]);
			}
			return arr;
		}
	}
}