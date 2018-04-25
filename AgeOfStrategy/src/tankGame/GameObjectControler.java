package tankGame;

import java.util.ArrayList;

/* Контроллер объектов управляет поведением всех выделенных ему объектов
* Как он это делает?
* Выдает задания для объектов в соответствии с их свойствами
* А откуда свойства беруться?
* Из модели!
* Хорошо, а как задать действия всем объектам сразу!?
* Управлять действиями определенной группы, определенным контроллером.
* А над ними ставить еще одного, общего.
* Или управлять определенными объектами через один контроллер.
* Уже теплее! А как сделать их действия синхронными?
* Заставить всех ждать общий тик времени!
* А внутренние процессы рассчетов выполнять в отдельном потоке каждого контроллера.
*/

public class GameObjectControler implements Runnable {
	
	String controllerName;
	Thread controller;
	
	GameObjectControler( String controllerName) {
		this.controllerName = controllerName;
		controller = new Thread(this, controllerName);
	}
	
	void handleEvents() {
	
	}
	/* Управлять появлением объектов будет контроллер.
	* Удаление (разрушение) объекта будет событием объета.
	* Так правильно во всяком случае!
	* Но контроллер тоже может его вызвать и что тогда?
	* Если удаление объекта вызывается самим объектом, 
	* значит об этом нужно уведомлять контроллера.
	* А для этого нужен стэк событий контроллера.
	* У событий есть сложность!
	* А как рассмотреть такое событие, как соприкосновение двух объектов?
	* Добавить как событие вроде "Contact" с условием - только для
	* материальных объетов. А данные брать от общего менеджера 
	* позиционирования, который перед следующим тиком будет генерировать 
	* события соприкосновения объетов!
	* Какие у события должны быть параметры?
	* Направление соприкосновения, прочность для генерации другого
	* события, вроде поломки или разрушения, в перспективе вес и скорость!
	*/
	
	void addObjects() {
		GameCore.AIObjects.add(new GameObject("light_tank", 400, 400));
		GameCore.AIObjects.add(new GameObject("light_tank", 600, 600));
		GameCore.AIObjects.add(new GameObject("light_tank", 400, 600));
		GameCore.AIObjects.add(new GameObject("light_tank", 600, 400));
	}
	
	void giveCommand(String command, int objectId) {
		GameCore.AIObjects.get(objectId).setCommand(command);
	}
	
	synchronized void handleGameTik() throws InterruptedException {
		for(GameObject x: GameCore.AIObjects) {
			x.handleCommands();
		}
		System.out.println("All commands handled" + "\n");
		wait();
	}
	
	synchronized public void tickEnd() {
		notify();
	}
	
	public void startController() {
		controller.start();
	}
	
	public void remioveController() throws InterruptedException {
		controller.join();
	}
	
	public void run() {
			addObjects();
			giveCommand("move", 0);
			giveCommand("rotate", 0);
			giveCommand("move", 1);
			giveCommand("rotate", 1);
			giveCommand("move", 2);
			giveCommand("rotate", 2);
			giveCommand("move", 3);
			giveCommand("rotate", 3);
			
			while(true) {
			try {
				handleGameTik();
			} catch(InterruptedException Ex) {Ex.printStackTrace();}
		}
	}
}
