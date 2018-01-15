package ageOfStrategyGame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

import gameSourceLib.*;
import tankGame.*;

public class Core implements MouseInputListener, MouseMotionListener, ComponentListener, WindowListener, WindowStateListener, ActionListener, Runnable
{
	private static final long serialVersionUID = 1L;
	
	JFrame mainWindow = new JFrame();
	
	// --- Window variables ---
	
	int widthMod = 28;
	int heightMod = 50;
	int startWidth;
	int startHeight;
	
	boolean isKeyPressed[] = new boolean[256];
	boolean isTitleScreenLoaded;
	boolean isOptionsScreenLoaded;
	boolean isGameScreenLoaded;
	boolean isQuitPressed = false;
	
	Thread worldGenerator;
	
	// --- Titel menu variables --- 
	
	JPanel titleScreen;
	JLabel titleMenu;
	
	JButton titleButton1;
	JButton titleButton2;
	JButton titleButton3;
	JButton titleButton4;
	
	// --- Game screen variables ---
	
	AgeOfStrategyScreen ageOfStrategyGameScreen;
	TankGameScreen tankGameScreen;
	SidePanel sideBarArea;
	
	boolean tankGame = false;
	
	// --- Options screen variables ---
	
	JPanel optionsScreen;
	JLabel optionsMenu;
	
	JCheckBox setFullScreen;
	boolean fullScreen = false;
	
	JButton optionsAccept;
	JButton backToMain;
	
	// --- Game World variables ---
	
	WorldLoader worldLoader;
	
	// --- | ---
	
	Core(int width, int height)
	{	
		startWidth = width;
		startHeight = height;	
		
		loadMainWindow(width, height, fullScreen, "main");
		
		worldGenerator = new Thread(this, "World Generator");
		worldGenerator.start();
	}
	
	void loadMainWindow(int width, int height, boolean fullScreen, String initiator)
	{
		mainWindow.addComponentListener(this);
		mainWindow.addWindowListener(this);
		
		mainWindow.setSize(width, height);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLayout(new FlowLayout());
		
		mainWindow.setUndecorated(fullScreen);
		mainWindow.setResizable(!fullScreen);
		
		this.fullScreen = fullScreen;
		
		if(fullScreen) mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		else mainWindow.setExtendedState(JFrame.NORMAL);
		
		mainWindow.setVisible(true);
		
		switch (initiator)
		{
		case "main":
			loadTitleScreen(width, height);
			break;
			
		case "optionsMenu":
			loadOptionsScreen(width, height);
			break;
		}
	}
	
	void loadTitleScreen(int width, int height) //Загрузка главного экрана
	{
		unloadActive();
		
		titleScreen = new JPanel();
		titleScreen.setLayout(new FlowLayout());
		titleScreen.setPreferredSize(new Dimension(width - widthMod, height - heightMod));
		titleScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		titleMenu = new JLabel();
		titleMenu.setPreferredSize(new Dimension((width / 4) - widthMod, (height / 3) - heightMod));
		titleMenu.setLayout(new GridLayout(4, 1, 5, 5));
		
		titleButton1 = new JButton("Start new game");
		titleButton1.addActionListener(this);
		titleMenu.add(titleButton1);
		
		titleButton2 = new JButton("Load game");
		titleButton2.addActionListener(this);
		titleMenu.add(titleButton2);
		
		titleButton3 = new JButton("Options");
		titleButton3.addActionListener(this);
		titleMenu.add(titleButton3);
		
		titleButton4 = new JButton("Quit");
		titleButton4.addActionListener(this);
		titleMenu.add(titleButton4);
		
		titleScreen.add(titleMenu, SwingUtilities.CENTER);
		mainWindow.add(titleScreen);
		
		isTitleScreenLoaded = true;
		SwingUtilities.updateComponentTreeUI(mainWindow);
	}
	
	void unloadTitleScreen() //Выгрузка главного экрана
	{
		try
		{
			mainWindow.remove(titleScreen);
			
			isTitleScreenLoaded = false;
		}
		catch(NullPointerException Ex)
		{
			
		}
		titleScreen = null;
	}
	
	void loadOptionsScreen(int width, int height) //Загрузка экрана настроек
	{
		unloadActive();
		
		optionsScreen = new JPanel();
		optionsScreen.setLayout(new FlowLayout());
		optionsScreen.setPreferredSize(new Dimension(width - widthMod, height - heightMod));
		optionsScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		optionsMenu = new JLabel();
		optionsMenu.setPreferredSize(new Dimension((width / 4) - widthMod, (height / 3) - heightMod));
		optionsMenu.setLayout(new GridLayout(4, 1, 5, 5));
		
		setFullScreen = new JCheckBox("Full screen mode");
		if(fullScreen) setFullScreen.setSelected(true);
		else setFullScreen.setSelected(false);
		optionsMenu.add(setFullScreen);
		
		optionsAccept = new JButton("Accept");
		optionsAccept.addActionListener(this);
		optionsMenu.add(optionsAccept);
		
		backToMain = new JButton("Back to main menu");
		backToMain.addActionListener(this);
		optionsMenu.add(backToMain);
		
		optionsScreen.add(optionsMenu);
		mainWindow.add(optionsScreen);
		
		isOptionsScreenLoaded = true;
		SwingUtilities.updateComponentTreeUI(mainWindow);
	}
	
	void unloadOptionsScreen() //Выгрузка экрана настроек
	{
		try
		{
			mainWindow.remove(optionsScreen);
		}
		catch(NullPointerException Ex)
		{
		
		}
		
		optionsScreen = null;
		isOptionsScreenLoaded = false;
	}
	
	void loadGameScreen(int width, int height)
	{
		unloadActive();
		
		mainWindow.setLayout(new FlowLayout());
		
		if(!tankGame)
		{	
			ageOfStrategyGameScreen = new AgeOfStrategyScreen();
			ageOfStrategyGameScreen.setPreferredSize(new Dimension((width / 6 * 5) - widthMod, height - heightMod));
			ageOfStrategyGameScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			mainWindow.add(ageOfStrategyGameScreen);
		}
		else
		{
			tankGameScreen = new TankGameScreen();
			tankGameScreen.setPreferredSize(new Dimension((width / 6 * 5) - widthMod, height - heightMod));
			tankGameScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			mainWindow.add(tankGameScreen);
		}
		
		sideBarArea = new SidePanel();
		sideBarArea.setPreferredSize(new Dimension((width / 6) - widthMod, height - heightMod));
		sideBarArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		sideBarArea.spButton1.addActionListener(this);
		sideBarArea.spButton2.addActionListener(this);
		sideBarArea.spButton3.addActionListener(this);
		sideBarArea.spButton4.addActionListener(this);
		
		
		mainWindow.add(sideBarArea);
		System.out.println("Now width: " + width + ", height: " + height);
		
		isGameScreenLoaded = true;
		
		SwingUtilities.updateComponentTreeUI(mainWindow);
	}
	
	void unloadGameScreen()
	{
		try
		{
			ageOfStrategyGameScreen.isActive = false;
			mainWindow.remove(ageOfStrategyGameScreen);
		}
		catch(NullPointerException Ex)
		{
			
		}
		
		try
		{
			mainWindow.remove(tankGameScreen);
		}
		catch(NullPointerException Ex)
		{
			
		}
			
		mainWindow.remove(sideBarArea);
		
		ageOfStrategyGameScreen = null;
		tankGameScreen = null;
		sideBarArea = null;
		
		isGameScreenLoaded = false;
	}
	
	void unloadActive()
	{
		if(isTitleScreenLoaded) unloadTitleScreen();
		if(isOptionsScreenLoaded) unloadOptionsScreen();
		if(isGameScreenLoaded) unloadGameScreen();
	}
	
	void gameLoop()
	{
		Calendar calendar = Calendar.getInstance();
		long delay = 0;
		
		long firstRequest;
		long lastRequest;
		
		while(!isQuitPressed)
		{
			firstRequest = calendar.getTimeInMillis();
			
			// --- Loop events ---
			
			// - Event 1. World update -
			
			if(worldLoader != null && isGameScreenLoaded) 
			{
				try
				{
					ageOfStrategyGameScreen.getWorldImage(worldLoader.world.getPlanetaryGraphics());
				}
				catch(NullPointerException Ex)
				{
					
				}
			}
			
			// --- Delay after calculations ---
			
			System.out.println("Dalay: " + delay);
			
			try
			{
				Thread.sleep(50 - (int) delay);
			}
			catch(InterruptedException Ex)
			{
				
			}
			
			lastRequest = calendar.getTimeInMillis();
			delay = lastRequest - firstRequest;
		}
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	public void mouseEntered(MouseEvent e)
	{
		
	}

	public void mouseExited(MouseEvent e) 
	{
		
	}

	public void mousePressed(MouseEvent e) 
	{
		
	}

	public void mouseReleased(MouseEvent e) 
	{
		
	}

	public void mouseDragged(MouseEvent e) 
	{
		
	}

	public void mouseMoved(MouseEvent e) 
	{
		
	}

	public void componentHidden(ComponentEvent arg0) 
	{
		
	}

	public void componentMoved(ComponentEvent arg0) 
	{
		
	}

	public void componentResized(ComponentEvent arg0) 
	{
	
		System.out.println("Now x:" + mainWindow.getWidth() + " y:" + mainWindow.getHeight());
		
		if(isGameScreenLoaded)
		{
			ageOfStrategyGameScreen.setPreferredSize(new Dimension((mainWindow.getWidth() / 6 * 5) - widthMod, mainWindow.getHeight() - heightMod));
			sideBarArea.setPreferredSize(new Dimension((mainWindow.getWidth() / 6) - widthMod, mainWindow.getHeight() - heightMod));
		}
		
		if(isTitleScreenLoaded) titleScreen.setPreferredSize(new Dimension(mainWindow.getWidth() - widthMod, mainWindow.getHeight() - heightMod));
		if(isOptionsScreenLoaded) optionsScreen.setPreferredSize(new Dimension(mainWindow.getWidth() - widthMod, mainWindow.getHeight() - heightMod));
		
		startWidth = mainWindow.getWidth();
		startHeight = mainWindow.getHeight();
		
		SwingUtilities.updateComponentTreeUI(mainWindow);
	}

	public void componentShown(ComponentEvent arg0) 
	{
		
	}

	public void windowActivated(WindowEvent arg0) 
	{
		
	}

	public void windowClosed(WindowEvent arg0) 
	{
		
	}

	public void windowClosing(WindowEvent arg0) 
	{
		
	}

	public void windowDeactivated(WindowEvent arg0) 
	{
		
	}

	public void windowDeiconified(WindowEvent arg0) 
	{
		System.out.println("Deiconified");
	}

	public void windowIconified(WindowEvent arg0) 
	{
		System.out.println("Iconified");
	}

	public void windowOpened(WindowEvent arg0) 
	{
		
	}

	public void windowStateChanged(WindowEvent Event) 
	{
		
	}

	public void actionPerformed(ActionEvent Event) 
	{
		switch (Event.getActionCommand())
		{
			case "Start new game":
				loadGameScreen(mainWindow.getWidth(), mainWindow.getHeight());
				break;

			case "Options":
				loadOptionsScreen(mainWindow.getWidth(), mainWindow.getHeight());
				break;
				
			case "Accept":
				if(setFullScreen.isSelected())
				{
					mainWindow.dispose();
					widthMod = 10;
					heightMod = 10;
					loadMainWindow(startWidth, startHeight, true, "optionsMenu");
				}
				else
				{
					mainWindow.dispose();
					widthMod = 28;
					heightMod = 50;
					loadMainWindow(startWidth, startHeight, false, "optionsMenu");
				}
				break;
				
			case "Back to main menu":
				loadTitleScreen(mainWindow.getWidth(), mainWindow.getHeight());
				break;
				
			case "Start Tank Game":
				tankGame = true;
				loadGameScreen(mainWindow.getWidth(), mainWindow.getHeight());
				break;
				
			case "Start Age of Strategy":
				tankGame = false;
				loadGameScreen(mainWindow.getWidth(), mainWindow.getHeight());
				break;
				
			case "Generate world":
				worldLoader = new WorldLoader(1264221224);
				break;
				
			case "Quit":
				
				isQuitPressed = true;
				try
				{
					worldGenerator.join();
				}
				catch(InterruptedException Ex)
				{
					
				}
				
				mainWindow.dispose();
				
				break;
		}
	}
	
	public void run()
	{
		gameLoop();
	}
}
