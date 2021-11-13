/**
 * ---------------------------------------------------------------------------
 * File name: Player.java
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
import weapon.Weapon;


/**
 * Player Class
 *
 * <hr>
 * Date created: Oct 31, 2018
 * <hr>
 * @authors Lucas Phillips, Brianna Martinson, Will Jennings
 */
public class Player extends Participant
{
	private Weapon weapon;
	
	
	/**
	 * Player Constructor        
	 *
	 * <hr>
	 * Date created: Nov 4, 2018 
	 *
	 * 
	 */
	public Player()
	{
		super(100,5,10,true);
		weapon = null;
	}
	
	/**
	 * Player Constructor        
	 *
	 * <hr>
	 * Date created: Nov 4, 2018 
	 *
	 * 
	 */
	public Player(int healthPoints, Weapon weapon)
	{
		super(healthPoints,5,10,true);
		this.weapon = weapon;
	}
	
	/**
	 * Returns amount of damage done to target, returns 0 if the Player misses         
	 *
	 * <hr>
	 * Date created: Nov 4, 2018 
	 *
	 * <hr>
	 * @return How much damage to deal
	 * @see participant.Participant#attack()
	 */
	public int attack()
	{
		int damage = this.damage;
		Random ran = new Random();
		
		if(weapon != null)
		{
			damage += weapon.getWeaponDMG ( );
		}
		
		if(ran.nextInt (100) < missChance)
		{
			damage = 0;
		}
		
		return damage;
	}
	
	/**
	 * Add a weapon to the Player         
	 *
	 * <hr>
	 * Date created: Nov 4, 2018
	 *
	 * <hr>
	 * @param weapon - Type of weapon to add to the Player
	 */
	public void pickUpWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}
	
	/**
	 * Check if player has a weapon         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Check if player has a weapon
	 */
	public boolean hasWeapon() {
		return !(weapon == null);	
	}
	
	/**
	 * Check if player has a weapon         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Check if player has a weapon
	 */
	public Weapon getWeapon() {
		return weapon;	
	}
}
