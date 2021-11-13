/**
 * ---------------------------------------------------------------------------
 * File name: AboutDialog.java
 * Project name: ZorkGUI
 * ---------------------------------------------------------------------------
 * Creator's name and email: Brianna Martinson, martinson@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Dec 1, 2018
 * ---------------------------------------------------------------------------
 */

package gameDriver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Creates the AboutDialog object
 * Provides information about the application to the user
 *
 * <hr>
 * Date created: Dec 1, 2018
 * <hr>
 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
 */
public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	JLabel projectNameLabel;
	JLabel projectName;
	JLabel authorsLabel;
	JTextArea authors;
	JLabel gameVersionLabel;
	JLabel gameVersion;
	JPanel messagePane;
	JPanel buttonPane;
	JButton okButton;

	/**
	 * Constructor for the AboutDialog object        
	 *
	 * <hr>
	 * Date created: Dec 3, 2018 
	 *
	 * 
	 * @param parent, the JFrame it is spawning from
	 * @param title, the title of the Dialog box
	 * @param message, the actual message displayed on the Dialog box
	 */
	public AboutDialog(JFrame parent, String title) 
	{
		super(parent, title, true);
		
		if (parent != null) 
		{
			Dimension parentSize = parent.getSize(); 
		    Point p = parent.getLocation(); 
		    setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
		}
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createDialog();
		pack(); 
		setVisible(true);
	}
	
	
	/**
	 * Load dialog elements        
	 *
	 * <hr>
	 * Date created: Dec 3, 2018 
	 *
	 *
	 */
	private void createDialog()
	{
		messagePane = new JPanel();
		messagePane.setLayout(new GridLayout(3,2));
		buttonPane = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(new ButtonClicked());
		projectNameLabel = new JLabel("Project Name: ");
		projectNameLabel.setHorizontalAlignment(JLabel.CENTER);
		authorsLabel = new JLabel("Authors: ");
		authorsLabel.setHorizontalAlignment(JLabel.CENTER);
		gameVersionLabel = new JLabel("Game Version: ");
		gameVersionLabel.setHorizontalAlignment(JLabel.CENTER);
		
		projectName = new JLabel("Zork");
		projectName.setHorizontalAlignment(JLabel.CENTER);
		authors = new JTextArea("Reid Conners\nWilliam Jennings\nBrianna Martinson\nLucas Phillips\nConor Powers-Stout");
		authors.setBackground(new Color(238, 238, 238));
		authors.setEditable(false);
		gameVersion = new JLabel("1.0");
		gameVersion.setHorizontalAlignment(JLabel.CENTER);
		
		authors.setFont(new Font("Courier",Font.BOLD,12));

		messagePane.add(projectNameLabel);
		messagePane.add(projectName);
		messagePane.add(authorsLabel);
		messagePane.add(authors);
		messagePane.add(gameVersionLabel);
		messagePane.add(gameVersion);
		
		buttonPane.add(okButton);
		
		add(messagePane, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.SOUTH);
	}
	
	/**
	 * Decides what happens when the user interacts with the buttons
	 *
	 * <hr>
	 * Date created: Dec 2, 2018
	 * <hr>
	 * @author Reid Conner, Conor Powers-Stout, William Jennings, Brianna Martinson, Lucas Phillips
	 */
	private class ButtonClicked implements ActionListener
	{
		/**
		 * Decides what happens when the user interacts with the JDialog box        
		 *
		 * <hr>
		 * Date created: Dec 3, 2018 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) 
		{
			setVisible(false); 
		    dispose(); 
		}
		  
		
	}
}