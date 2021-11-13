/**
 * ---------------------------------------------------------------------------
 * File name: Dungeon.java
 * Project name: Zork
 * ---------------------------------------------------------------------------
 * Creator's name and email: Lucas Phillips, phillipsls@etsu.edu,
 * 							Brianna Martinson, martinson@etsu.edu,
 * 							Will Jennings, jenningsw@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Oct 31, 2018
 * ---------------------------------------------------------------------------
 */

package dungeon;

import java.util.Random;
import participant.*;
import weapon.*;

/**
 * Dungeon Class
 *
 * <hr>
 * Date created: Oct 31, 2018
 * <hr>
 * @author Lucas Phillips, Will Jennings, Brianna Martinson
 */
public class Dungeon
{
	private Room[][] dungeon;
	private int rowAmount;
	private int colAmount;
	private int playerRow;
	private int playerCol;
	private int exitRow;
	Random ran;
	
	
	/**
	 * Default Constructor        
	 *
	 * <hr>
	 * Date created: Nov 7, 2018 
	 *
	 * 
	 */
	public Dungeon()
	{
		ran = new Random(System.currentTimeMillis());
		fillDungeon();
	}
	
	/**
	 * Default Constructor        
	 *
	 * <hr>
	 * Date created: Nov 7, 2018 
	 *
	 * 
	 */
	public Dungeon(Room[][] dungeon, int rowAmount, int colAmount, int playerRow, int playerCol, int exitRow)
	{
		this.dungeon = dungeon;
		this.rowAmount = rowAmount;
		this.colAmount = colAmount;
		this.playerRow = playerRow;
		this.playerCol = playerCol;
		this.exitRow = exitRow;
		ran = new Random(System.currentTimeMillis());
	}

	/**
	 * Return row player is in         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Return row player is in
	 */
	public int getPlayerRow() {
		return playerRow;
	}
	
	/**
	 * Return column player is in         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Return column player is in
	 */
	public int getPlayerCol() {
		return playerCol;
	}
	
	/**
	 * Return room object the player is in         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Return room object the player is in
	 */
	public Room getCurrentRoom() {
		return dungeon[playerRow][playerCol];
	}
	
	/**
	 * Return row that contains the dungeon exit         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Return row that contains the dungeon exit
	 */
	public int getExitRow() {
		return exitRow;
	}
	
	/**
	 * Return row that contains the dungeon exit         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Return row that contains the dungeon exit
	 */
	public int getExitCol() {
		return dungeon[0].length - 1;
	}
	
	/**
	 * Return dungeon      
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Return dungeon
	 */
	public Room[][] getDungeon() 
	{
		return dungeon;
	}

	
	/**
	 * Populate the array with rooms         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 */
	private void fillDungeon()
	{
		playerRow = 0;
		playerCol = 0;
		rowAmount = ran.nextInt(3) + 3;
		colAmount = ran.nextInt(5) + 5;
		
		dungeon = new Room[rowAmount][colAmount];
		
		for(int i = 0; i < rowAmount; i++)
		{
			for(int j = 0; j < colAmount; j++)
			{
				dungeon[i][j] = new Room(null, null, null);
			}
		}
		
		exitRow = ran.nextInt (rowAmount);
		
		addMonsters();
		addWeapon();
		dungeon[0][0].setPlayer (new Player());
		
	}
	
	/**
	 * Randomly add monsters to the dungeon         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 */
	private void addMonsters()
	{
		boolean monsterSpawns;
		int monsterType;
		
		for(int i = 0; i < rowAmount; i++)
		{
			for(int j = 0; j < colAmount; j++)
			{
				monsterSpawns = ran.nextBoolean ( );
				if(monsterSpawns)
				{
					monsterType = ran.nextInt (100);
					
					if(monsterType < 50)
					{
						dungeon[i][j].setMonster (new Kobold());
					}
					else if(monsterType < 85)
					{
						dungeon[i][j].setMonster (new Lizardman());
					}
					else
					{
						dungeon[i][j].setMonster (new Lich());
					}
				}
			}
		}
	}
	
	/**
	 * Randomly add a weapon to the dungeon         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 */
	private void addWeapon()
	{
		for(int i = 0; i < rowAmount; i++)
		{
			for(int j = 0; j < colAmount; j++)
			{
				int spawnWeapon = ran.nextInt (100);
				if(spawnWeapon < 10)
				{
					int weaponType = ran.nextInt (100);
					if(weaponType < 15)
					{
						dungeon[i][j].setWeapon (new Stick());
					}
					else if(weaponType < 40)
					{
						dungeon[i][j].setWeapon (new PoolNoodle());
					}
					else if(weaponType < 70)
					{
						dungeon[i][j].setWeapon (new Sword());
					}
					else if(weaponType < 90)
					{
						dungeon[i][j].setWeapon (new Naginata());
					}
					else
					{
						dungeon[i][j].setWeapon (new Warhammer());
					}
					return;
				}
			}
		}
	}
	
	
	/**
	 * Test if the player can move in the desired direction         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @param direction - The direction the player wishes to move
	 * @return - Boolean of whether the movement is possible
	 */
	public boolean canMove(String direction)
	{
		boolean canMove = false;
		
		switch(direction)
		{
			case "east":
				if(playerCol != (colAmount - 1) || playerRow == exitRow)
				{
					canMove = true;
				}
				break;
			case "west":
				if(playerCol != 0)
				{
					canMove = true;
				}
				break;
			case "north":
				if(playerRow != 0)
				{
					canMove = true;
				}
				break;
			case "south":
				if(playerRow != (rowAmount - 1))
				{
					canMove = true;
				}
				break;
		}
		
		return canMove;
	}
	
	/**
	 * Move the player in the desired direction         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @param direction - Direction the player wishes to move in
	 * @return - Returns the room the player has moved into
	 */
	public Room movePlayer(String direction)
	{
		Room returnRoom = null;
		switch(direction.toLowerCase ( ))
		{
			case "east":
				dungeon[playerRow][playerCol + 1].setPlayer (dungeon[playerRow][playerCol].getPlayer ( ));
				dungeon[playerRow][playerCol].setPlayer(null);
				playerCol++;
				returnRoom = dungeon[playerRow][playerCol];
				break;
			case "west":
				dungeon[playerRow][playerCol - 1].setPlayer (dungeon[playerRow][playerCol].getPlayer ( ));
				dungeon[playerRow][playerCol].setPlayer(null);
				playerCol--;
				returnRoom = dungeon[playerRow][playerCol];
				break;
			case "north":
				dungeon[playerRow - 1][playerCol].setPlayer (dungeon[playerRow][playerCol].getPlayer ( ));
				dungeon[playerRow][playerCol].setPlayer(null);
				playerRow--;
				returnRoom = dungeon[playerRow][playerCol];
				break;
			case "south":
				dungeon[playerRow + 1][playerCol].setPlayer (dungeon[playerRow][playerCol].getPlayer ( ));
				dungeon[playerRow][playerCol].setPlayer(null);
				playerRow++;
				returnRoom = dungeon[playerRow][playerCol];
				break;
		}
		
		return returnRoom;
	}
	
	/**
	 * Returns the dungeon in a readable string         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018 
	 *
	 * <hr>
	 * @return - Returns the dungeon in a readable string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String dungeonString = "";
		for(int j = 0; j < rowAmount; j++)
		{
			dungeonString += " ___";
		}
		for(int i = (colAmount - 1); i >= 0; i--)
		{
			dungeonString += "\n";
			dungeonString += "|";
			//System.out.println ();
			//System.out.print ("|");
			for(int j = 0; j < rowAmount; j++)
			{
				if(dungeon[j][i].getPlayer ( ) != null)
				{
					dungeonString += "P";
					if(dungeon[j][i].getMonster ( ) != null)
						dungeonString += "M";
					else
						dungeonString += "_";
					if(dungeon[j][i].getWeapon ( ) != null)
						dungeonString += "W";
					else
						dungeonString += "_";
				}
				else 
					dungeonString += "___";
				dungeonString += "|";
			}

		}

		return dungeonString;
		
	}
}
