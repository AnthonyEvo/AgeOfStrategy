package tankGame;

import java.util.ArrayList;
import javax.swing.*;

public class GameCore implements Runnable{
	
	static ArrayList<GameObject> AIObjects = new ArrayList<GameObject>(0);
	static ArrayList<GameObject> amAIObjects = new ArrayList<GameObject>(0);
	static ArrayList<GameObject> playerObjects = new ArrayList<GameObject>(0);
	
	static ArrayList<GameObjectControler> controllers = new ArrayList<GameObjectControler>(1);
	
	
	/*
	 * Контроллера нельзя просто создать, его нужно где-то регистрировать
	 * потому, что он должен слушать игровые тики.
	 * Игровые объекты нужно разделить на группы, по признакам 
	 * и потом уже группы передавать контроллерам
	 * если нужно то сразу несколько или даже все!
	 */
	
	GameRegion nowRegion = null;
	
	public GameCore() {
		Thread core = new Thread(this, "Game core");
		core.start();
	}
	
	// Тут загрузим для игры региончик
	void loadGameRegion() {
		
	}

	// Тут игровой тик стартует
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
	
	//Тут игровой тик приостанавливается
	void pauseGameLoop() {
		
	}
	
	// Пока будем считать что контроллеры грузятся одновременно
	void loadControllers() {
		controllers.add(new GameObjectControler("AI controller"));
	}
	
	/*
	 * Но контроллер тоже может отключится! Что делать с отключенным?
	 * Поставить условие наличия или отсутствия объектов.
	 * И если контроллер больше не может повлять на игру, отключать его!
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
