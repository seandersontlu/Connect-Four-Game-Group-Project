// Original: Coy T, Jordyn M, David G
// Scott, Montrell, Sarah, Adrian
// CSCI 434 Project #1, Iteration #4
// ConnectFourClientGUI.java
// 3-21-19
//
// This will create the frame for the Connect four game.

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/** This class represents the client GUI that the user will see and use
 * @author Coy T, Jordyn M, David G
 * @author Scott, Montrell, Sarah, Adrian
 */
public class ConnectFourClientGUI extends JFrame implements ConnectFourConstants, Observer
{
    public static final String server = "localhost";
    private String player1;
    private String player2;
    private int score1;
    private int score2;

    private JButton start;
    private JButton restart;
    private JButton quit;
    private JButton[] num;

    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel infoLabel;
    private ConnectFourClient client;
    private ConnectFourModel model;
    private boolean waitingForPlayer1;
    private boolean haveChip;

    public static void main(String[] args)
    {
	    ConnectFourClientGUI window = new ConnectFourClientGUI();
	    window.pack();
	    window.setVisible(true);
    }

    public ConnectFourClientGUI()
    {
	    super("Connect Four");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Making the button

        quit = new JButton("Quit");
        quit.addActionListener(new ButtonListener());
        
        num = new JButton[8];
        for (int i = 0; i < num.length; i++)
        {
            num[i] = new JButton(" " + i + "  ");
            num[i].addActionListener(new ButtonListener());
        }

        // Adding Player names, scores, and turn status
        
 	    JPanel statPanel = new JPanel ();
        statPanel.setLayout(new BorderLayout());

        JPanel playerPanel = new JPanel();
        player1Label = new JLabel(" ");
        player2Label = new JLabel(" ");

        JPanel turnPanel = new JPanel();
        JLabel turnLabel = new JLabel("Player: ");
        infoLabel = new JLabel("Waiting to start... ");

        playerPanel.add(player1Label);
        playerPanel.add(player2Label);
        turnPanel.add(infoLabel);

        statPanel.add(playerPanel, BorderLayout.NORTH);
        statPanel.add(turnPanel, BorderLayout.SOUTH);


        // Panels

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Connect 4 Game");
        titlePanel.setBackground(Color.GREEN);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JPanel numPanel = new JPanel();
        numPanel.setBackground(Color.WHITE);

        model = new ConnectFourModel();

        // Add components to the panel

        titlePanel.add(title);
        buttonPanel.add(quit);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3,1));
        topPanel.add(buttonPanel);

        for (int i = 1; i < num.length; i++)
            numPanel.add(num[i]);

        topPanel.add(numPanel);
        topPanel.add(titlePanel);
        setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(model, BorderLayout.CENTER);
        this.add(statPanel, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(500,500));
        
        player1 = "";
        player2 = "";
        enterName();
        score1 = 0;
        score2 = 0;
        haveChip = false;
        waitingForPlayer1 = true;

        client = new ConnectFourClient();
        client.addObserver(this);

    }

    /** Update the board and put the chip in the correct column
     * @param o what is being observed  
     * @param obj the object that is being returned
     */
    public void update(Observable o, Object obj)
    {
        if (obj instanceof String)
        {
            String text = (String) obj;
	        if (text.contains("won"))
	        {
	            infoLabel.setText(text);
	            for (int i = 0; i < num.length; i++)
	                num[i].setEnabled(false);
       	    }
	        else
	            infoLabel.setText(text);
        } 
        else if (obj instanceof Integer)
        {

            int col = (Integer) obj;
            model.drop(col - 1);
            if (waitingForPlayer1)
	        {
                infoLabel.setText("It is player 1's turn.");
	        }
            else
                infoLabel.setText("It is player 2's turn.");
            

            if (!haveChip)
            {
		        if (waitingForPlayer1)
			        infoLabel.setText("It is Player 2's turn.");
		        else
		            infoLabel.setText("It is Player 1's turn.");
			
			    haveChip = true;
            }
	        else
	        {
	            haveChip = false;

	            waitingForPlayer1 = !waitingForPlayer1;
	        }
	        model.changePlayer();
    	}       
    }
    
    /** Enters the player's names
     */
    public void enterName()
    {
	    if (player1.equals(""))
		{
		    player1 = JOptionPane.showInputDialog("Enter your name: ");
		    player1Label.setText(player1 + ": " + score1);
		}
		else
		{
		    player2 = JOptionPane.showInputDialog("Enter your name: ");
		    player2Label.setText(player2 + ": " + score2);
	    }

    }
    
    /** Puts the chip in the desired column and sends signal to client that
     *    it is ready to drop a chip
     */
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
	
	        int column = 0;
            if (event.getSource() == quit)
                System.exit(0);
	    

	        for (int i = 0; i < num.length; i++)
	        {
		        if (event.getSource() == num[i])
		        {
		            column = i;
		            client.readyToDropChip(column);
		        }
	        }
    	}
    }
    
}

