/**
 * ---------------------------------------------------------------------------
 * File name: Weapon.java
 * Project name: Zork
 * ---------------------------------------------------------------------------
 * Creator's name and email: Lucas Phillips, phillipsls@etsu.edu,
 * 							Brianna Martinson, martinson@etsu.edu,
 * 							Will Jennings, jenningsw@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Oct 31, 2018
 * ---------------------------------------------------------------------------
 */

package weapon;


/**
 * Weapon parent class
 *
 * <hr>
 * Date created: Oct 31, 2018
 * <hr>
 * @authors Lucas Phillips, Brianna Martinson, Will Jennings
 */
public class Weapon
{
	protected int weaponDMG;
	protected String weaponType;
	
	
	/**
	 * Parameterized Constructor        
	 *
	 * <hr>
	 * Date created: Nov 4, 2018 
	 *
	 * 
	 * @param weaponDMG - Amount of damage the weapon adds
	 */
	public Weapon(int weaponDMG, String weaponType)
	{
		this.weaponDMG = weaponDMG;
		this.weaponType = weaponType;
	}
	
	/**
	 * Returns weapon damage         
	 *
	 * <hr>
	 * Date created: Nov 4, 2018
	 *
	 * <hr>
	 * @return - Returns weapon damage
	 */
	public int getWeaponDMG()
	{
		return this.weaponDMG;
	}
	
	/**
	 * Sets weapon damage         
	 *
	 * <hr>
	 * Date created: Nov 4, 2018
	 *
	 * <hr>
	 * @param weaponDMG - Amount of damage to set damage to
	 */
	public void setWeaponDMG(int weaponDMG)
	{
		this.weaponDMG = weaponDMG;
	}
	
	/**
	 * Return type of weapon         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @return - Return type of weapon
	 */
	public String getWeaponType() {
		return weaponType;
	}

	/**
	 * Set the weapon         
	 *
	 * <hr>
	 * Date created: Nov 7, 2018
	 *
	 * <hr>
	 * @param weaponType - Set the weapon
	 */
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	/**
	 * Readable info about weapon         
	 *
	 * <hr>
	 * Date created: Nov 4, 2018 
	 *
	 * <hr>
	 * @return a String to represent a weapon
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ( )
	{
		return "Weapon Type: \t\t" + weaponType + "\nWeapon Damage: \t\t" + weaponDMG;
	}
	
	
}
