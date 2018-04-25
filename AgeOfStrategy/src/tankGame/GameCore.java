package tankGame;

import java.util.ArrayList;
import javax.swing.*;

public class GameCore implements Runnable{
	
	static ArrayList<GameObject> AIObjects = new ArrayList<GameObject>(0);
	static ArrayList<GameObject> amAIObjects = new ArrayList<GameObject>(0);
	static ArrayList<GameObject> playerObjects = new ArrayList<GameObject>(0);
	
	static ArrayList<GameObjectControler> controllers = new ArrayList<GameObjectControler>(1);
	
	
	/*
	 * ����������� ������ ������ �������, ��� ����� ���-�� ��������������
	 * ������, ��� �� ������ ������� ������� ����.
	 * ������� ������� ����� ��������� �� ������, �� ��������� 
	 * � ����� ��� ������ ���������� ������������
	 * ���� ����� �� ����� ��������� ��� ���� ���!
	 */
	
	GameRegion nowRegion = null;
	
	public GameCore() {
		Thread core = new Thread(this, "Game core");
		core.start();
	}
	
	// ��� �������� ��� ���� ���������
	void loadGameRegion() {
		
	}

	// ��� ������� ��� ��������
	void startGameLoop() {
		for(GameObjectControler x: controllers) {
			x.startController();
		}
		
		
		while(true) {
			try {
				Thread.sleep(50);				
				updateScreen();	

				for(GameObjectControler x: controllers) {
					int count = 0;
					x.tickEnd();
				}

			} catch(Exception Ex) {Ex.printStackTrace();}
			
		}
	}
	
	//��� ������� ��� ������������������
	void pauseGameLoop() {
		
	}
	
	// ���� ����� ������� ��� ����������� �������� ������������
	void loadControllers() {
		controllers.add(new GameObjectControler("AI controller"));
	}
	
	/*
	 * �� ���������� ���� ����� ����������! ��� ������ � �����������?
	 * ��������� ������� ������� ��� ���������� ��������.
	 * � ���� ���������� ������ �� ����� ������� �� ����, ��������� ���!
	 */
	
	public void run() {
		loadControllers();
		startScreen();
		startGameLoop();
	}
	
	JFrame frame;
	TankGameScreen screen;
	
	void startScreen() {
		frame = new JFrame();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		screen = new TankGameScreen();
		frame.add(screen);
		frame.setVisible(true);
	}
	
	void updateScreen() {	
		for(GameObject x: AIObjects) {
			screen.setData(x.getLocate(false));
		}
		screen.reDraw();
		
	}
}
