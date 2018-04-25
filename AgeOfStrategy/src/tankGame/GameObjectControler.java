package tankGame;

import java.util.ArrayList;

/* ���������� �������� ��������� ���������� ���� ���������� ��� ��������
* ��� �� ��� ������?
* ������ ������� ��� �������� � ������������ � �� ����������
* � ������ �������� ��������?
* �� ������!
* ������, � ��� ������ �������� ���� �������� �����!?
* ��������� ���������� ������������ ������, ������������ ������������.
* � ��� ���� ������� ��� ������, ������.
* ��� ��������� ������������� ��������� ����� ���� ����������.
* ��� ������! � ��� ������� �� �������� �����������?
* ��������� ���� ����� ����� ��� �������!
* � ���������� �������� ��������� ��������� � ��������� ������ ������� �����������.
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
	/* ��������� ���������� �������� ����� ����������.
	* �������� (����������) ������� ����� �������� ������.
	* ��� ��������� �� ������ ������!
	* �� ���������� ���� ����� ��� ������� � ��� �����?
	* ���� �������� ������� ���������� ����� ��������, 
	* ������ �� ���� ����� ���������� �����������.
	* � ��� ����� ����� ���� ������� �����������.
	* � ������� ���� ���������!
	* � ��� ����������� ����� �������, ��� ��������������� ���� ��������?
	* �������� ��� ������� ����� "Contact" � �������� - ������ ���
	* ������������ �������. � ������ ����� �� ������ ��������� 
	* ����������������, ������� ����� ��������� ����� ����� ������������ 
	* ������� ��������������� �������!
	* ����� � ������� ������ ���� ���������?
	* ����������� ���������������, ��������� ��� ��������� �������
	* �������, ����� ������� ��� ����������, � ����������� ��� � ��������!
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
