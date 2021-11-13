/**
 * ---------------------------------------------------------------------------
 * File name: GameDriver.java
 * Project name: ZorkGUI
 * ---------------------------------------------------------------------------
 * Creator's name and email: Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
 * Course:  CSCI 1260
 * Creation Date: Nov 30, 2018
 * ---------------------------------------------------------------------------
 */


package gameDriver;

import javax.swing.SwingUtilities;



/**
 * Run GameWindow
 *
 * <hr>
 * Date created: Dec 3, 2018
 * <hr>
 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
 */

/**
 * Enter type purpose here
 *
 * <hr>
 * Date created: Dec 3, 2018
 * <hr>
 * @author Don Bailes
 */
public class GameDriver {

	
	/**
	 * Run GameWindow         
	 *
	 * <hr>
	 * Date created: Dec 3, 2018
	 *
	 * <hr>
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(
				new Runnable() {
					@Override
					public void run()
					{
						new GameWindow();
					}
				}
		);

	}

}
