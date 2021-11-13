/**
 * ---------------------------------------------------------------------------
 * File name: Participant.java
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


/**
 * Participant parent class
 *
 * <hr>
 * Date created: Oct 31, 2018
 * <hr>
 * @authors Lucas Phillips, Brianna Martinson, Will Jennings
 */
public abstract class Participant
{
	protected int healthPoints;
	protected int damage;
	protected int missChance;
	protected boolean alive;
	
	
	/**
	 * Parameterized Constructor        
	 *
	 * <hr>
	 * Date created: Nov 4, 2018 
	 *
	 * 
	 * @param healthPoints - Amount of health points the participant has
	 * @param damage - Amount of damage the participant does
	 * @param missChance - Probability the participant will miss
	 * @param alive - Boolean if the participant is alive
	 */
	public Participant(int healthPoints, int damage, int missChance, boolean alive)
	{
		this.healthPoints = healthPoints;
		this.damage = damage;
		this.missChance = missChance;
		this.alive = alive;
	}
	
	/**
	 * Apply damage to a participant        
	 *
	 * <hr>
	 * Date created: Nov 4, 2018
	 *
	 * <hr>
	 * @param damageAmount - Amount of damage done to the participant
	 */
	public void injury(int damageAmount)
	{
		healthPoints -= damageAmount;
		
		if(healthPoints <= 0)
		{
			healthPoints = 0;
			alive = false;
		}
	}
	
	
	/**
	 * @return healthPoints
	 */
	public int getHealthPoints ( )
	{
		return healthPoints;
	}

	
	/**
	 * @param set the healthPoints to healthPoints
	 */
	public void setHealthPoints (int healthPoints)
	{
		this.healthPoints = healthPoints;
	}

	
	/**
	 * @return damage
	 */
	public int getDamage ( )
	{
		return damage;
	}

	
	/**
	 * @param set the damage to damage
	 */
	public void setDamage (int damage)
	{
		this.damage = damage;
	}

	
	/**
	 * @return missChance
	 */
	public int getMissChance ( )
	{
		return missChance;
	}

	
	/**
	 * @param set the missChance to missChance
	 */
	public void setMissChance (int missChance)
	{
		this.missChance = missChance;
	}

	
	/**
	 * @return alive
	 */
	public boolean isAlive ( )
	{
		return alive;
	}

	
	/**
	 * @param set the alive to alive
	 */
	public void setAlive (boolean alive)
	{
		this.alive = alive;
	}

	/**
	 * Do damage to another Participant         
	 *
	 * <hr>
	 * Date created: Nov 4, 2018
	 *
	 * <hr>
	 * @return How much damage to deal
	 */
	protected abstract int attack();
}
