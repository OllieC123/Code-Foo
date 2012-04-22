/**
 * ConnectFour.java
 *
 * Implements ConnectFourGUI, ConnectFourModel, and Minimax to create
 *  functional Connect Four game.
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConnectFour extends JFrame// implements KeyListener
{
		//Initial position of chip
		private int initr = 3;
		private int initc = -1;

		private ConnectFourModel cfm = new ConnectFourModel();
		private ConnectFourGUI gui = new ConnectFourGUI(cfm,initr,initc);


		//New game difficulty dialog box
		final JOptionPane newGame = new JOptionPane();
		Object[] options = {"EASY",
                    "INTERMEDIATE", "HARD"};
		//Difficulty (2 is easy, 4 is med, 6 is hard)
		private int diff = 0;
		private String showDiff = "";

    /**
     * Creates a new instance of <code>ConnectFour</code>.
     */
   		public ConnectFour()
   		{
   			//Panel components at top
   			JButton left = new JButton("Move Left");
   			JButton down = new JButton("Move Down");
   			JButton right = new JButton("Move Right");

   			JPanel topPanel = new JPanel();
   			topPanel.setLayout(new FlowLayout());
   			topPanel.add(left);
   			topPanel.add(down);
   			topPanel.add(right);

   			//Panel components at bottom

			JButton reset = new JButton("Start new game");
			JButton newDiff = new JButton("Choose difficulty");
   			JPanel botPanel = new JPanel();
   			botPanel.setLayout(new FlowLayout());
			chooseDiff();
			botPanel.add(reset);
			botPanel.add(newDiff);



   			//Listeners for each button
   			left.addActionListener(new leftListener());
   			right.addActionListener(new rightListener());
			down.addActionListener(new downListener());
			reset.addActionListener(new resetListener());
			newDiff.addActionListener(new newDiffListener());

			//Content panel containing top panel and game boarad
   			JPanel content = new JPanel();
   			content.setLayout(new BorderLayout());
   			content.add(topPanel, BorderLayout.NORTH);
   			content.add(gui, BorderLayout.CENTER);
   			content.add(botPanel, BorderLayout.SOUTH);

   			setContentPane(content);
   			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   			pack();
   			setResizable(false);
    	}

    	class resetListener implements ActionListener
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			cfm.reset();
    			//Sets difficulty to given one.
    			gui.setDiff(diff);
    			//Set displayed difficulty.
    			if (diff == 2)
    			{
    			showDiff = ("Current difficulty: EASY");
    			}
    			else if (diff == 4)
    			{
    			showDiff = ("Current difficulty: INTERMEDIATE");
    			}
    			else if (diff == 6)
    			{
    			showDiff = ("Current difficulty: HARD");
    			}
    			gui.repaint();

    		}
    	}

		class newDiffListener implements ActionListener
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			chooseDiff();
    		}
    	}

		//If Move Left button is clicked, the chip is moved left.
    	class leftListener implements ActionListener
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			int col = gui.chipCol();
    			if (col <= 6 && col >0)
    			{
    				gui.setCol(col-1);
    				gui.repaint();
    			}
    		}
    	}

    	//Select difficulty method.
    	private void chooseDiff()
    	{
    		//If diff == 0 (no games have been played), then do not show message dialogs.
    		if (diff == 0)
    		{
    			int i = newGame.showOptionDialog(null,
 						   showDiff,
  						   "Select difficulty",
 							   JOptionPane.YES_NO_OPTION,
  							  JOptionPane.QUESTION_MESSAGE,
   							 null,     //do not use a custom Icon
   							 options,  //the titles of buttons
   							 options[0]); //default button title
   				if (i == 0)
    			{
    			diff = 2;
    			showDiff = ("Current difficulty: EASY");
    			}
    			else if (i == 1)
    			{
    			diff = 4;
    			showDiff = ("Current difficulty: INTERMEDIATE");
    			}
    			else if (i == 2)
    			{
    			diff = 6;
    			showDiff = ("Current difficulty: HARD");
    			}
    			//Make sure a difficulty is chosen.
    			else if (showDiff=="")
    			{newGame.showMessageDialog(null,
 						   "Please try again.",
  						   "Error",JOptionPane.WARNING_MESSAGE);
    			chooseDiff();
    			}
    		}

			// If diff is already established, show message dialogs.
    		else
    		{

    		int i = newGame.showOptionDialog(null,
 						   showDiff,
  						   "Select difficulty",
 							   JOptionPane.YES_NO_OPTION,
  							  JOptionPane.QUESTION_MESSAGE,
   							 null,     //do not use a custom Icon
   							 options,  //the titles of buttons
   							 options[0]); //default button title
    		if (i == 0)
    		{
    			diff = 2;
    			newGame.showMessageDialog(null,
 						   "Difficulty will be changed to EASY upon new game.",
  						   "EASY",JOptionPane.WARNING_MESSAGE);
    		}
    		else if (i == 1)
    		{
    			diff = 4;
    			newGame.showMessageDialog(null,
 						   "Difficulty will be changed to INTERMEDIATE upon new game.",
  						   "INTERMEDIATE",JOptionPane.WARNING_MESSAGE);
    		}
    		else if (i == 2)
    		{
    			diff = 6;
    			newGame.showMessageDialog(null,
 						   "Difficulty will be changed to HARD upon new game.",
  						   "HARD",JOptionPane.WARNING_MESSAGE);
    		}
    		}
    	}

		//If Move Right button is clicked, the chip is moved right.
    	class rightListener implements ActionListener
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			int col = gui.chipCol();
    			if (col < 6 && col >=0)
    			{
    				gui.setCol(col+1);
    				gui.repaint();
    			}
    		}
    	}

    	//If Move Down button is clicked, the chip is moved down to the lowest row.
    	// The computer acts as well.
    	class downListener implements ActionListener
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			int col = gui.chipCol();
    			int dropRow = cfm.drop(col);
    			if (dropRow != -1)
    			{
    				gui.setCol(col);
    				gui.repaint();

    			}
    		}
    	}


    /**
     * @param args the command line arguments
     */
    	public static void main(String[] args)
    	{
    		new ConnectFour().setVisible(true);
  		}
}
