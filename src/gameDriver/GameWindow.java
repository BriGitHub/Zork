/**
 * ---------------------------------------------------------------------------
 * File name: GameWindow.java
 * Project name: ZorkGUI
 * ---------------------------------------------------------------------------
 * Creator's name and email: Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
 * Course:  CSCI 1260
 * Creation Date: Nov 30, 2018
 * ---------------------------------------------------------------------------
 */

package gameDriver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import dungeon.Dungeon;
import dungeon.Room;
import participant.*;
import weapon.*;

/**
 * Creates the GUI window that will run the game
 *
 * <hr>
 * Date created: Dec 2, 2018
 * <hr>
 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
 */
public class GameWindow extends JFrame 
{
	private static final String NORTH = "north";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String SOUTH = "south";
	
	private final static long serialVersionUID = 1L;
	private JButton forwardButton;
	private JButton backButton;
	private JButton leftButton;
	private JButton rightButton;
	private JLabel mainImage;
	private JTextArea battleLog;
	private JLabel health;
	private JTextArea map;
	
	private JPanel southPanel;
	private JPanel centerPanel;
	private JPanel healthMapPanel;
	private JPanel battleLogPanel;
	private JPanel buttonPanel;
	
	private JScrollPane battleLogScrollPane;
	
	private JMenuBar menuBar;
	private JMenuItem pause;
	
	private Dungeon dungeon = new Dungeon();
	private Room currentRoom = dungeon.getCurrentRoom();
	private Timer timer;
	
	private Clip clip;
	private int screenWidth;
	private int screenHeight;
	/**
	 * Constructor for a GameWindow GUI object        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018 
	 *
	 * 
	 */
	public GameWindow()
	{
		super("Zork");
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setUndecorated(true);
		
		try 
		{
			this.setIconImage(ImageIO.read(new File("assets/Icon/Skull.png")));
		} 
		catch (IOException e) 
		{
			
		}
		
		instantiateObjects();
		addElements();
		addListeners();
		createMenu();
		
		this.setVisible(true);
		
		runGame();
	}
	
	/**
	 * Create's the GameWindow's menu         
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void createMenu()
	{
		menuBar = new JMenuBar();
		pause = new JMenuItem("Pause");
		pause.addActionListener(new ButtonListener());getContentPane();
		pause.setPreferredSize(new Dimension(200,50));
		pause.setFont(new Font("Courier New", Font.BOLD, 20));
		menuBar.add(pause);
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Creates all the objects the GameWindow has        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void instantiateObjects()
	{
		forwardButton = new JButton();
		forwardButton.setIcon(new ImageIcon("assets/Direction_Arrow/North.png"));
		backButton = new JButton();
		backButton.setIcon(new ImageIcon("assets/Direction_Arrow/South.png"));
		leftButton = new JButton();
		leftButton.setIcon(new ImageIcon("assets/Direction_Arrow/West.png"));
		rightButton = new JButton();
		rightButton.setIcon(new ImageIcon("assets/Direction_Arrow/East.png"));
		
		mainImage = new JLabel();
		ImageIcon picture1 = new ImageIcon("assets/Corridor.png");
		Image newImage = picture1.getImage().getScaledInstance((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2), Image.SCALE_SMOOTH);
		picture1 = new ImageIcon(newImage);
		mainImage.setIcon(picture1);
		
		battleLog = new JTextArea();
		health = new JLabel();
		map = new JTextArea("Map");
		
		southPanel = new JPanel(new GridLayout(1,3));
		centerPanel = new JPanel(new GridLayout(1,2));
		battleLogPanel = new JPanel(new GridLayout(2,1));
		healthMapPanel = new JPanel(new GridLayout(1,2));
		buttonPanel = new JPanel(new GridLayout(3,3));
		
		southPanel.setBackground(new Color(75, 75, 75));
		centerPanel.setBackground(new Color(75, 75, 75));
		battleLogPanel.setBackground(new Color(75, 75, 75));
		healthMapPanel.setBackground(new Color(75, 75, 75));
		buttonPanel.setBackground(new Color(75, 75, 75));
		
		battleLogScrollPane = new JScrollPane(battleLog);
		
		Border padding = BorderFactory.createEmptyBorder(10,10,10,10);
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
		centerPanel.setBorder(padding);
		mainImage.setBorder(padding);
		battleLog.setBorder(padding);
		
		health.setHorizontalAlignment(JLabel.CENTER);
		health.setBorder(blackBorder);
		map.setBorder(blackBorder);
		battleLogScrollPane.setBorder(blackBorder);
		mainImage.setBorder(blackBorder);
		
		battleLog.setFont(new Font("Courier New", Font.ITALIC, 14));
		battleLog.setForeground(new Color(200, 200, 200));
		battleLog.setBackground(new Color(100, 100, 100));
		
		map.setFont(new Font("Courier New", Font.ITALIC, 20));
		map.setForeground(new Color(200, 200, 200));
		map.setBackground(new Color(100, 100, 100));
	}
	
	/**
	 * Adds all the elements onto the GameWindow window        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void addElements()
	{
		buttonPanel.add(new JLabel());
		buttonPanel.add(forwardButton, BorderLayout.NORTH);
		buttonPanel.add(new JLabel());
		buttonPanel.add(leftButton, BorderLayout.WEST);
		buttonPanel.add(new JLabel());
		buttonPanel.add(rightButton, BorderLayout.SOUTH);
		buttonPanel.add(new JLabel());
		buttonPanel.add(backButton, BorderLayout.EAST);
		
		southPanel.add(new JLabel());
		southPanel.add(buttonPanel);
		southPanel.add(new JLabel());
		
		healthMapPanel.add(health);
		healthMapPanel.add(map);
		
		//battleLogScrollPane.add(battleLog);
		
		battleLogPanel.add(healthMapPanel);
		battleLogPanel.add(battleLogScrollPane);
		
		centerPanel.add(mainImage);
		centerPanel.add(battleLogPanel);
		
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Adds the listeners to all the buttons       
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void addListeners()
	{
		forwardButton.addActionListener(new ButtonListener());
		backButton.addActionListener(new ButtonListener());
		leftButton.addActionListener(new ButtonListener());
		rightButton.addActionListener(new ButtonListener());
	}
	
	/**
	 * Runs the main aspects of the core of the game Zork        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void runGame()
	{
		playDungeonMusic();
		printIntro();
		map.setText(dungeon.toString());
		updateFrame();
		if(dungeon.getCurrentRoom().hasWeapon())
		{
			findWeapon();
		}
		
		if(dungeon.getCurrentRoom().hasMonster() && dungeon.getCurrentRoom().getMonster().isAlive())
		{
			startBattle();
		}
	}
	
	/**
	 * Welcome the user and explain the game         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 */
	private void printIntro() {
		battleLog.setText(battleLog.getText() + "Welcome to Zork!" +
						"\n\n" +
						"--------------------------------------------------------------------------------------------------\n" +
						"In this game, there is a dungeon with at most 5 rows and 10 columns of cells. \r\n" + 
						"\r\n" + 
						"The player begins in the bottom leftmost cell and tries to find the  \r\n" + 
						"\r\n" + 
						"exit of the dungeon that is somewhere along the top of the dungeon. \r\n" + 
						"\r\n" + 
						"A successful exit from the exit cell wins the game. \r\n" + 
						"\r\n" + 
						"Along the way each cell may contain a monster in it that must be deafeted to move on. \r\n" + 
						"\r\n" + 
						"You may also find a weapon that does additional damage to the monsters along the way. \r\n" + 
						"\r\n" + 
						"  \r\n" + 
						"\r\n" + 
						"The game continues until the player escapes through the exit or is defeated by a monster. \r\n" + 
						"\r\n" + 
						"In each cell, a player may move to any direction aslong as it does not go out of bounds \r\n" + 
						"\r\n" + 
						"from the dungeon. \n" +
						"--------------------------------------------------------------------------------------------------\n");
	}

	/**
	 * Battle between player and monster until one of them is dead         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 */
	private void battle()
	{
		Player player = currentRoom.getPlayer();
		Monster monster = currentRoom.getMonster();
		int damage;
		imageUpdate();
		if(!player.isAlive()) {
			try
			{
				Image image = ImageIO.read(new File("assets/Death_Screen/Death_Screen.png")).getScaledInstance(screenWidth / 2, screenHeight / 2, Image.SCALE_DEFAULT);
				mainImage.setIcon(new ImageIcon(image));
			}
			catch(Exception e)
			{
				
			}
			JOptionPane.showMessageDialog(null, "You died!", "Zork", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Icon/Skull.png"));
			System.exit(-8);
		}
		else if(!monster.isAlive())
		{
			battleLog.setText(battleLog.getText() + "\n\tThe " + monster.getMonsterName() + " is dead.");
		
			currentRoom.setPlayer(player);
			currentRoom.setMonster(null);
			
			timer.stop();
			clip.stop ( );
			playVictorySound();
			ActionListener listener = new ActionListener(){
		        public void actionPerformed(ActionEvent event){
		            playDungeonMusic();
		            pause.setEnabled(true);
		            updateFrame();
		        }
		    };
			Timer startDungeonMusic = new Timer(1500, listener);
			startDungeonMusic.setRepeats (false);
			startDungeonMusic.start ( );
		}
		
		if(monster.isAlive()) {
			
			damage = player.attack();
			if(damage == 0) {
				battleLog.setText(battleLog.getText() + "\n\tThe player missed the " + monster.getMonsterName() + ".");
			}
			else{
				monster.injury(damage);
				battleLog.setText(battleLog.getText() + "\n\tThe " + monster.getMonsterName() + " was hit: points = " + monster.getHealthPoints());
			}
		}
		
		if(monster.isAlive()) {
			
			damage = monster.attack();
			if(damage == 0) {
				battleLog.setText(battleLog.getText() + "\n\tThe " + monster.getMonsterName() + " missed the player.");
			}
			else{
				player.injury(damage);
				battleLog.setText(battleLog.getText() + "\n\tThe player was hit: points = " + player.getHealthPoints());
			}
		}
	}
	
	/**
	 * Creates the function that runs the battle when the user runs into a monster 
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void startBattle()
	{
		battleLog.setText(battleLog.getText() + "\nA " + dungeon.getCurrentRoom().getMonster().getMonsterName() + " appeared! Fight for your life!");
		updateFrame();
		clip.stop ( );
		playBattleSound();
		forwardButton.setEnabled(false);
		backButton.setEnabled(false);
		leftButton.setEnabled(false);
		rightButton.setEnabled(false);
		pause.setEnabled(false);

		ActionListener listener = new ActionListener(){
	        public void actionPerformed(ActionEvent event){
	            battle();
	        }
	    };
		timer = new Timer(500, listener);
	    timer.setRepeats(true);
	    timer.start();
	}
	
	/**
	 * Ask if user wants to pick up the weapon         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 */
	private void findWeapon() {
		int choice;
		choice = JOptionPane.showConfirmDialog(null, "You found a " + currentRoom.getWeapon().getWeaponType() + "(+" + currentRoom.getWeapon().getWeaponDMG() + " DMG)! Would you like to pick up the weapon?","Zork",JOptionPane.INFORMATION_MESSAGE);
		if(choice == JOptionPane.YES_OPTION) {
			battleLog.setText(battleLog.getText() + "\nYou picked up the " + currentRoom.getWeapon().getWeaponType() + "! You now do " + currentRoom.getWeapon().getWeaponDMG() + " additional damage. Your total damage dealt is " + (currentRoom.getPlayer().getDamage() + currentRoom.getWeapon().getWeaponDMG()) + ".");
			currentRoom.getPlayer().pickUpWeapon(currentRoom.getWeapon());
			currentRoom.setWeapon(null);
			updateFrame();
		}
		else {
			battleLog.setText(battleLog.getText() + "\nYou decided not to pick up the " + currentRoom.getWeapon().getWeaponType() + ".");
		}
	}
	
	/**
	 * Updates the images on screen and what buttons are active        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void updateFrame()
	{
		
		forwardButton.setEnabled(dungeon.canMove(EAST));
		backButton.setEnabled(dungeon.canMove(WEST));
		leftButton.setEnabled(dungeon.canMove(NORTH));
		rightButton.setEnabled(dungeon.canMove(SOUTH));
		
		map.setText(dungeon.toString());
		imageUpdate();
		updateHealth();
		
	}
	
	/**
	 * Update the player's health icon        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void updateHealth()
	{
		if(dungeon.getCurrentRoom().getPlayer().getHealthPoints() == 100)
		{
			health.setIcon(new ImageIcon("assets/Health_Bar/Health_Bar_Full.png"));
			return;
		}
		if(dungeon.getCurrentRoom().getPlayer().getHealthPoints() > 60)
		{
			health.setIcon(new ImageIcon("assets/Health_Bar/Health_Bar_80.png"));
			return;
		}
		if(dungeon.getCurrentRoom().getPlayer().getHealthPoints() > 40)
		{
			health.setIcon(new ImageIcon("assets/Health_Bar/Health_Bar_60.png"));
			return;
		}
		if(dungeon.getCurrentRoom().getPlayer().getHealthPoints() > 20)
		{
			health.setIcon(new ImageIcon("assets/Health_Bar/Health_Bar_40.png"));
			return;
		}
		if(dungeon.getCurrentRoom().getPlayer().getHealthPoints() > 0)
		{
			health.setIcon(new ImageIcon("assets/Health_Bar/Health_Bar_20.png"));
			return;
		}
		else
		{
			health.setIcon(new ImageIcon("assets/Health_Bar/Health_Bar_Empty.png"));
		}
	}
	
	/**
	 * Update the Lizardman image base on health        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 * @return String, reflects the picture that goes with the Lizardman's health
	 */
	private String lizardmanPNGPath()
	{
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 15)
		{
			return "assets/Lizardman/Lizardman.png";
		}
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 10)
		{
			return "assets/Lizardman/Lizardman_Death_1.png";
		}
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 5)
		{
			return "assets/Lizardman/Lizardman_Death_2.png";
		}
		return "assets/Lizardman/Lizardman_Death_3.png";
	}
	
	/**
	 * Update the Lich's image based on health        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 * @return String, reflects the picture that goes with the Lich's health
	 */
	private String lichPNGPath()
	{
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 15)
		{
			return "assets/Lich/Lich.png";
		}
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 10)
		{
			return "assets/Lich/Lich_Death_1.png";
		}
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 5)
		{
			return "assets/Lich/Lich_Death_2.png";
		}
		return "assets/Lich/Lich_Death_3.png";
	}
	
	/**
	 * Update the Kobold's image based on health        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 * @return String, reflects the picture that goes with the Kobold's health
	 */
	private String koboldPNGPath()
	{
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 15)
		{
			return "assets/Kobald/Kobald.png";
		}
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 10)
		{
			return "assets/Kobald/Kobald.png";
		}
		if(dungeon.getCurrentRoom().getMonster().getHealthPoints() > 5)
		{
			return "assets/Kobald/Kobald_Death_2.png";
		}
		return "assets/Kobald/Kobald_Death_3.png";
	}
	
	/**
	 * 
	 * plays dungeon music       
	 *
	 * <hr>
	 * Date created: Dec 3, 2018
	 *
	 * <hr>
	 */
	private void playDungeonMusic()
	{
		try
		{
			File soundFile = new File("assets/Sounds/dungeon.wav"); //you could also get the sound file with an URL
        
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
	        clip.start();
		}
		catch (UnsupportedAudioFileException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * plays a sound for defeating a monster        
	 *
	 * <hr>
	 * Date created: Dec 3, 2018
	 *
	 * <hr>
	 */
	private void playVictorySound()
	{
		try
		{
			File soundFile = new File("assets/Sounds/win.wav"); //you could also get the sound file with an URL
        
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
	        clip.start();
	        
	        
		}
		catch (UnsupportedAudioFileException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}
	
	/**
	 * Plays sound when battle begins        
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void playBattleSound()
	{
		try
		{
			File soundFile = new File("assets/Sounds/battle.wav"); //you could also get the sound file with an URL
        
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
	        clip.start();
		}
		catch (UnsupportedAudioFileException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	 
	}
	/**
	 * 
	 * Updates the images on screen. Uses the images in the assets folder.         
	 *
	 * <hr>
	 * Date created: Dec 3, 2018
	 *
	 * <hr>
	 */
	private void imageUpdate()
	{
		
		try {
			Image bckimage = ImageIO.read(new File("assets/Corridor/Corridor_Left.png"));; 
			Image monsterImage = null;
			Image weaponImage = null;
			
			screenWidth = (int)(Toolkit.getDefaultToolkit ( ).getScreenSize ( ).getWidth ( ));
			screenHeight = (int)(Toolkit.getDefaultToolkit ( ).getScreenSize ( ).getHeight ( ));
			if(!dungeon.canMove(NORTH) && !dungeon.canMove(EAST))
			{
				bckimage = ImageIO.read(new File("assets/Corridor/Corridor_Back_Left.png"));
			}
			else if(!dungeon.canMove(SOUTH) && !dungeon.canMove(EAST))
			{
				bckimage = ImageIO.read(new File("assets/Corridor/Corridor_Back_Right.png"));
			}
			else if(!dungeon.canMove(EAST))
			{
				bckimage = ImageIO.read(new File("assets/Corridor/Corridor_Back_Center.png"));
			}
			else if(!dungeon.canMove (NORTH)) 
			{
				bckimage = ImageIO.read(new File("assets/Corridor/Corridor_Left.png"));
			}
			else if (!dungeon.canMove ("south"))
			{
				bckimage = ImageIO.read(new File("assets/Corridor/Corridor_Right.png"));
			}
			else
			{
				bckimage = ImageIO.read(new File("assets/Corridor/Corridor.png"));
			}
			
			//find what monster the player is fighting
			if(currentRoom.getMonster() instanceof Kobold)
			{
				monsterImage = ImageIO.read(new File(koboldPNGPath()));
			}														
			else if(currentRoom.getMonster() instanceof Lich)                                   
			{
				monsterImage = ImageIO.read(new File(lichPNGPath()));
			}
			else if(currentRoom.getMonster() instanceof Lizardman)
			{
				monsterImage = ImageIO.read(new File(lizardmanPNGPath()));
			}
			else
			{
				monsterImage = null;
			}
			
			//find what weapon the player is holding
			if(dungeon.getCurrentRoom().getPlayer().getWeapon() instanceof Naginata)
			{
				weaponImage = ImageIO.read(new File("assets/Weapons/Naginata/Naginata.png"));
			}
			else if(dungeon.getCurrentRoom().getPlayer().getWeapon() instanceof PoolNoodle)
			{
				weaponImage = ImageIO.read(new File("assets/Weapons/Pool_Noodle/Pool_Noodle.png"));
			}
			else if(dungeon.getCurrentRoom().getPlayer().getWeapon() instanceof Stick)
			{
				weaponImage = ImageIO.read(new File("assets/Weapons/Stick/Stick.png"));
			}
			else if(dungeon.getCurrentRoom().getPlayer().getWeapon() instanceof Sword)
			{
				weaponImage = ImageIO.read(new File("assets/Weapons/Sword/Sword.png"));
			}
			else if(dungeon.getCurrentRoom().getPlayer().getWeapon() instanceof Warhammer)
			{
				weaponImage = ImageIO.read(new File("assets/Weapons/WarHammer/Warhammer.png"));
			}
			else
			{
				weaponImage = null;
			}
			
			BufferedImage combined = new BufferedImage(screenWidth/2, screenHeight/2, BufferedImage.TYPE_INT_ARGB);//TODO get the width and hieght of the cell to create the image
		    //paint both images, preserving the alpha channels
		    Graphics g = combined.getGraphics();
		    g.drawImage(bckimage.getScaledInstance(screenWidth/2, screenHeight/2, Image.SCALE_DEFAULT), 0, 0, null);
		    
		    
		    if(dungeon.getCurrentRoom().getPlayer().hasWeapon())
		    {
		    	g.drawImage(weaponImage.getScaledInstance ((screenHeight/10), (screenHeight/10), Image.SCALE_DEFAULT), (screenWidth/2)-(screenHeight/10), (screenHeight/2)-(screenHeight/10), null);
			   //ImageIO.write(combined, "PNG", new File("assets/gameBlank.PNG"));
		    }
		    else
		    {
		    	//ImageIO.write(combined, "PNG", new File("assets/gameBlank.PNG"));
		    }
		    //Image gameBlank = ImageIO.read(new File("assets/gameBlank.PNG"));
		    if(dungeon.getCurrentRoom().hasMonster() && dungeon.getCurrentRoom().getMonster().isAlive())
		    {
		    	 g.drawImage(monsterImage.getScaledInstance ((screenWidth/4), (screenHeight/4), Image.SCALE_FAST), (screenWidth/4)-(screenWidth/8), (screenHeight/4)-(screenHeight/8), null);
		    	 
		    	 //ImageIO.write(combined, "PNG", new File("assets/game.PNG"));
		    	 //for(int i = 0; i < 3; i++)
		    	// {
		    		// g.drawImage(gameBlank.getScaledInstance(screenWidth/2, screenHeight/2, Image.SCALE_DEFAULT), 0, 0, null);
		    		// g.drawImage(monsterImage.getScaledInstance ((screenWidth/4), (screenHeight/4), Image.SCALE_FAST), (screenWidth/4)-(screenWidth/8), (screenHeight/4)-(screenHeight/8), null);
		    		// ImageIO.write(combined, "PNG", new File("assets/game"+(i+1)+".PNG"));		    		 
		    	// }
		    }
		    else
		    {
		    	//ImageIO.write(combined, "PNG", new File("assets/game.PNG"));
		    }
		    mainImage.setIcon(new ImageIcon(combined));
		    combined = null;
		    g = null;
		    monsterImage = null;
		    weaponImage = null;
		    bckimage = null;
		    
		}
		catch(Exception e)
		{
			System.out.println (e.getMessage ( ));
		}
	}
	
	/**
	 * Tells the user they won once they reach the end       
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	private void endGame()
	{
		try
		{
			Image image = ImageIO.read(new File("assets/Win_Screen/Win_Screen.png")).getScaledInstance(screenWidth / 2, screenHeight / 2, Image.SCALE_DEFAULT);
			mainImage.setIcon(new ImageIcon(image));
		}
		catch(Exception e)
		{
			
		}
		JOptionPane.showMessageDialog(null, "Congratulations! You have won Zork!", "Zork", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Icon/Skull.png"));
		System.exit(0);
	}
	
	/**
	 * Save the user's progress to a text file      
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	public void saveGame()
	{
		JFileChooser fileChooser = new JFileChooser("saves");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files","txt","text");
		fileChooser.setFileFilter(filter);
		int status = fileChooser.showSaveDialog(null);
		if(status != JFileChooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(null, "You did not select a file!", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Icon/Skull.png"));
			return;
		}
		
		String fileLocation = fileChooser.getSelectedFile().getPath();
		
		if(!fileLocation.toLowerCase().endsWith(".txt") && !fileLocation.toLowerCase().endsWith(".text"))
		{
			fileLocation += ".txt";
		}
		
		try 
		{
			PrintWriter outputFile = new PrintWriter(fileLocation);
			outputFile.println(dungeon.getDungeon().length);
			outputFile.println(dungeon.getDungeon()[0].length);
			outputFile.println(dungeon.getCurrentRoom().getPlayer().getHealthPoints());
			if(dungeon.getCurrentRoom().getPlayer().hasWeapon())
				outputFile.println(dungeon.getCurrentRoom().getPlayer().getWeapon().getWeaponType());
			else
				outputFile.println("-");
			outputFile.println(dungeon.getPlayerRow());
			outputFile.println(dungeon.getPlayerCol());
			outputFile.println(dungeon.getExitRow());
			for(int i = 0; i < dungeon.getDungeon().length; i++)
			{
				for(int j = 0; j < dungeon.getDungeon()[0].length; j++)
				{
					if(dungeon.getDungeon()[i][j].hasMonster())
					{
						outputFile.println(dungeon.getDungeon()[i][j].getMonster().getMonsterName());
					}
					else
					{
						outputFile.println("-");
					}
					if(dungeon.getDungeon()[i][j].hasWeapon())
					{
						outputFile.println(dungeon.getDungeon()[i][j].getWeapon().getWeaponType());
					}
					else
					{
						outputFile.println("-");
					}
				}
			}
			outputFile.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null, "File Corrupted!", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Icon/Skull.png"));
		}
	}
	
	/**
	 * Load the player's progress from a text file    
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 * <hr>
	 */
	public void loadGame()
	{
		int rowLength;
		int colLength;
		int healthPoints;
		String weaponType;
		int playerRow;
		int playerCol;
		int exitRow;
		Room[][] dungeon;
		
		JFileChooser fileChooser = new JFileChooser("saves");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files","txt","text");
		fileChooser.setFileFilter(filter);
		int status = fileChooser.showOpenDialog(null);
		if(status != JFileChooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(null, "You did not select a file!", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Icon/Skull.png"));
			return;
		}
		
		File chosenFileLocation = fileChooser.getSelectedFile();
		
		try
		{
			Scanner inputFile = new Scanner(chosenFileLocation);
			rowLength = Integer.parseInt(inputFile.nextLine());
			colLength = Integer.parseInt(inputFile.nextLine());
			healthPoints = Integer.parseInt(inputFile.nextLine());
			weaponType = inputFile.nextLine();
			playerRow = Integer.parseInt(inputFile.nextLine());
			playerCol = Integer.parseInt(inputFile.nextLine());
			exitRow = Integer.parseInt(inputFile.nextLine());
			dungeon = new Room[rowLength][colLength];
			
			for(int i = 0; i < rowLength; i++)
			{
				for(int j = 0; j < colLength; j++)
				{
					String monster = inputFile.nextLine();
					String weapon = inputFile.nextLine();
					
					dungeon[i][j] = new Room(createMonster(monster),null,createWeapon(weapon));
				}
			}
			
			dungeon[playerRow][playerCol].setPlayer(new Player(healthPoints,createWeapon(weaponType)));
			
			this.dungeon = new Dungeon(dungeon, rowLength, colLength, playerRow, playerCol, exitRow);
			updateFrame();
			inputFile.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "File Corrupted!", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Icon/Skull.png"));
			e.printStackTrace();
		}
	}
	
	/**
	 * Convert monster string to object   
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 *
	 *@param monsterName - String of monster
	 * <hr>
	 */
	private Monster createMonster(String monsterName)
	{
		switch(monsterName)
		{
			case "Kobold":
				return new Kobold();
			case "Lizardman":
				return new Lizardman();
			case "Lich":
				return new Lich();
		}
		return null;
	}
	
	/**
	 * Convert weapon string to object   
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 *
	 *
	 *@param weaponName - String of weapon
	 * <hr>
	 */
	private Weapon createWeapon(String weaponName)
	{
		switch(weaponName)
		{
			case "Naginata":
				return new Naginata();
			case "Pool Noodle":
				return new PoolNoodle();
			case "Stick":
				return new Stick();
			case "Sword":
				return new Sword();
			case "Warhammer":
				return new Warhammer();
		}
		return null;
	}
	
	/**
	 * Decides what happens when the user interacts with the buttons
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 * <hr>
	 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
	 */
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent buttonClick) {
			if(buttonClick.getSource().equals(forwardButton))
			{
				if(dungeon.canMove(EAST))
				{
					if((dungeon.getPlayerRow() == dungeon.getExitRow()) && dungeon.getPlayerCol() == dungeon.getExitCol())
					{
						endGame();
					}
					currentRoom = dungeon.movePlayer(EAST);
					map.setText(dungeon.toString());
					if(dungeon.getCurrentRoom().hasWeapon())
					{
						findWeapon();
					}
					
					
					if(dungeon.getCurrentRoom().hasMonster() && dungeon.getCurrentRoom().getMonster().isAlive())
					{
						startBattle();
					}
					else
					{
						updateFrame();
					}
				}
			}
			
			else if(buttonClick.getSource().equals(backButton))
			{
				if(dungeon.canMove(WEST))
				{
					currentRoom = dungeon.movePlayer(WEST);
					map.setText(dungeon.toString());
					if(dungeon.getCurrentRoom().hasWeapon())
					{
						findWeapon();
					}
					
					if(dungeon.getCurrentRoom().hasMonster() && dungeon.getCurrentRoom().getMonster().isAlive())
					{
						startBattle();
					}
					else
					{
						updateFrame();
					}
				}
			}
			
			else if(buttonClick.getSource().equals(leftButton))
			{
				if(dungeon.canMove(NORTH))
				{
					currentRoom = dungeon.movePlayer(NORTH);
					map.setText(dungeon.toString());
					if(dungeon.getCurrentRoom().hasWeapon())
					{
						findWeapon();
					}
					
					if(dungeon.getCurrentRoom().hasMonster() && dungeon.getCurrentRoom().getMonster().isAlive())
					{
						startBattle();
					}
					else
					{
						updateFrame();
					}
				}
			}
			
			else if(buttonClick.getSource().equals(rightButton))
			{
				if(dungeon.canMove(SOUTH))
				{
					currentRoom = dungeon.movePlayer(SOUTH);
					map.setText(dungeon.toString());
					if(dungeon.getCurrentRoom().hasWeapon())
					{
						findWeapon();
					}
					
					if(dungeon.getCurrentRoom().hasMonster() && dungeon.getCurrentRoom().getMonster().isAlive())
					{
						startBattle();
					}
					else
					{
						updateFrame();
					}
				}
			}
			
			else if(buttonClick.getSource().equals(pause))
			{
				ActionListener saveListener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			            saveGame();
			        }
			    };
			    ActionListener loadListener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			            loadGame();
			        }
			    };
			    ActionListener resumeListener = new ActionListener() {
			    	public void actionPerformed(ActionEvent event) {
			    		playDungeonMusic();
			    	}
			    };
			    clip.stop();
				new Pause(saveListener, loadListener, resumeListener);
			}
			
			
		}
		
	}
}
