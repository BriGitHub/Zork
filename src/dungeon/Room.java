/**
 * ---------------------------------------------------------------------------
 * File name: Room.java
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

import weapon.*;
import participant.*;


/**
 * Room Class
 *
 * <hr>
 * Date created: Oct 31, 2018
 * <hr>
 * @author Lucas Phillips, Will Jennings, Brianna Martinson
 */
public class Room
{
	private Monster monster;
	private Player player;
	private Weapon weapon;
	
	
	/**
	 * Parameterized Constructor        
	 *
	 * <hr>
	 * Date created: Nov 7, 2018 
	 *
	 * 
	 * @param monster
	 * @param player
	 * @param weapon
	 */
	public Room(Monster monster, Player player, Weapon weapon)
	{
		this.monster = monster;
		this.player = player;
		this.weapon = weapon;
	}

	
	/**
	 * @return monster
	 */
	public Monster getMonster ( )
	{
		return monster;
	}

	
	/**
	 * @param set the monster to monster
	 */
	public void setMonster (Monster monster)
	{
		this.monster = monster;
	}

	
	/**
	 * @return player
	 */
	public Player getPlayer ( )
	{
		return player;
	}

	
	/**
	 * @param set the player to player
	 */
	public void setPlayer (Player player)
	{
		this.player = player;
	}

	
	/**
	 * @return weapon
	 */
	public Weapon getWeapon ( )
	{
		return weapon;
	}

	
	/**
	 * @param set the weapon to weapon
	 */
	public void setWeapon (Weapon weapon)
	{
		this.weapon = weapon;
	}
	
	/**
	 * Returns true if the room contains the player         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Returns true if the room contains the player
	 */
	public boolean hasPlayer() {
		return !(player == null);
	}
	
	/**
	 * Returns true if the room contains a monster         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Returns true if the room contains a monster
	 */
	public boolean hasMonster() {
		return !(monster == null);
	}
	
	/**
	 * Returns true if the room contains a weapon        
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Returns true if the room contains a weapon
	 */
	public boolean hasWeapon() {
		return !(weapon == null);
	}
	
}
