/**
 * ---------------------------------------------------------------------------
 * File name: Monster.java
 * Project name: Zork
 * ---------------------------------------------------------------------------
 * Creator's name and email: Lucas Phillips, phillipsls@etsu.edu,
 * 							Brianna Martinson, martinson@etsu.edu,
 * 							Will Jennings, jenningsw@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Oct 31, 2018
 * ---------------------------------------------------------------------------
 */

package participant;

import java.util.Random;

/**
 * Represents a Monster in the game
 *
 * <hr>
 * Date created: Oct 31, 2018
 * <hr>
 * @authors Lucas Phillips, Brianna Martinson, Will Jennings
 */
public class Monster extends Participant
{
	protected String monsterName;
	
	/**
	 * Monster Constructor        
	 *
	 * <hr>
	 * Date created: Nov 4, 2018 
	 *
	 * 
	 * @param healthPoints - Amount of health points the monster has
	 * @param damage - Amount of damage the monster does
	 * @param missChance - Probability the monster will miss
	 * @param alive - Boolean if the monster is alive
	 */
	public Monster(String name, int healthPoints, int damage, int missChance, boolean alive)
	{
		super(healthPoints, damage, missChance, alive);
		monsterName = name;
	}
	
	public void setMonsterName(String name) {
		monsterName = name;
	}
	
	public String getMonsterName() {
		return monsterName;
	}
	
	/**
	 * Returns amount of damage that the target receives, returns 0 if the monster misses         
	 *
	 * <hr>
	 * Date created: Nov 4, 2018 
	 *
	 * <hr>
	 * @return - Returns amount of the damage the target receives, returns 0 if the monster misses
	 * @see participant.Participant#attack()
	 */
	public int attack()
	{
		int damage = this.damage;
		Random ran = new Random();
		
		if(ran.nextInt (100) < missChance)
		{
			damage = 0;
		}
		
		return damage;
	}
}
