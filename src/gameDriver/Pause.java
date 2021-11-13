/**
 * ---------------------------------------------------------------------------
 * File name: Pause.java
 * Project name: ZorkGUI
 * ---------------------------------------------------------------------------
 * Creator's name and email: Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Philips
 * Course:  CSCI 1260
 * Creation Date: Nov 30, 2018
 * ---------------------------------------------------------------------------
 */

package gameDriver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The pause menu for the user to stop their current game
 *
 * <hr>
 * Date created: Nov 30, 2018
 * <hr>
 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
 */
public class Pause extends JFrame
{
	private static final long serialVersionUID = 1L;
	final int WINDOW_WIDTH = 250,
			WINDOW_HEIGHT = 300;
	
	JLabel pauseLabel;
	JButton resume;
	JButton save;
	JButton load;
	JButton about;
	JButton endGame;
	
	JFrame pauseFrame = this;
	
	ActionListener saveListener;
	ActionListener loadListener;
	ActionListener resumeListener;
	
	/**
	 * Constructor for the Pause object       
	 *
	 * <hr>
	 * Date created: Nov 30, 2018 
	 *
	 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
	 */
	public Pause (ActionListener saveListener, ActionListener loadListener, ActionListener resumeListener)
	{
		super("");
		
		this.saveListener = saveListener;
		this.loadListener = loadListener;
		this.resumeListener = resumeListener;
		
		try {
			this.setIconImage(ImageIO.read(new File("assets/Icon/Skull.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		this.setUndecorated(true);
		
		instantiateObjects();
		
		setLocationRelativeTo(null); //Change null to game window
		setVisible(true);
	}
	
	private void instantiateObjects()
	{
		pauseLabel = new JLabel("Game Paused");
		resume = new JButton("Resume Game");
		save = new JButton("Save Game");
		load = new JButton("Load Game");
		about = new JButton("About");
		endGame = new JButton("End Game");
		
		pauseLabel.setPreferredSize(new Dimension(200, 30));
		pauseLabel.setHorizontalAlignment(JLabel.CENTER);
		pauseLabel.setFont(new Font("Courier New", Font.BOLD, 20));
		resume.setPreferredSize(new Dimension(200, 35));
		save.setPreferredSize(new Dimension(200, 35));
		load.setPreferredSize(new Dimension(200, 35));
		about.setPreferredSize(new Dimension(200, 35));
		endGame.setPreferredSize(new Dimension(200, 35));
		
		resume.addActionListener(resumeListener);
		resume.addActionListener(new ResumeButtonClicked());
		save.addActionListener(saveListener);
		load.addActionListener(loadListener);
		about.addActionListener(new AboutButtonClicked());
		endGame.addActionListener(new EndButtonClicked());
		
		add(pauseLabel);
		add(resume);
		add(save);
		add(load);
		add(about);
		add(endGame);
	}
	
	
	/**
	 * Sets up the action meant to happen when the user
	 * presses the button that says "Resume"
	 *
	 * <hr>
	 * Date created: Nov 30, 2018
	 * <hr>
	 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
	 */
	private class ResumeButtonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}

	
	/**
	 * Sets up the action meant to happen when the user
	 * presses the button that says "End"
	 *
	 * <hr>
	 * Date created: Nov 30, 2018
	 * <hr>
	 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
	 */
	private class EndButtonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
		
	/**
	 * Sets up the action meant to happen when the user
	 * presses the button that says "About"
	 *
	 * <hr>
	 * Date created: Nov 30, 2018
	 * <hr>
	 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
	 */
	private class AboutButtonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new AboutDialog(pauseFrame, "About");
		}
	}
}
