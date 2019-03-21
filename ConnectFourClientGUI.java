// Coy T, Jordyn M, David G
// CSCI 434 Project #1, Iteration #2
// ConnectFourClientGUI.java
// 2-18-19
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
    private JLabel title;
    private JButton start;
    private JButton restart;
    private JButton quit;
    private JButton[] num;
    private JPanel buttonPanel;
    private JPanel numPanel;
    private JPanel titlePanel;
    
    private JLabel infoLabel;
    private ConnectFourClient client;
    private ConnectFourModel model;
    private boolean waitingForPlayer1;
    private boolean haveChip;
    private int column = 0;

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

        restart = new JButton("New Game");
        restart.addActionListener(new ButtonListener());
        quit = new JButton("Quit");
        quit.addActionListener(new ButtonListener());
        
        num = new JButton[8];
        for (int i = 1; i < num.length; i++)
        {
            num[i] = new JButton(" " + i + "  ");
            num[i].addActionListener(new ButtonListener());
        }

        // Adding Player names
        
        Panel p2 = new Panel();
        p2.setLayout(new BorderLayout());
        
        JLabel playerOne = new JLabel("Player 1:");
        p2.add(playerOne, BorderLayout.SOUTH);
        JLabel playerTwo = new JLabel("Player 2:");
        p2.add(playerTwo, BorderLayout.SOUTH);

        enterPlayer();

        playerOne.setText(player1);
        playerTwo.setText(player2);

        JPanel names = new JPanel();
        JLabel name1 = new JLabel("Player 1 (Yellow):");
        JLabel nameOne = new JLabel(player1);
        JLabel name2 = new JLabel(" Player 2 (Red):");
        JLabel nameTwo = new JLabel(player2);
	infoLabel = new JLabel(" ");
        names.add(name1);
        names.add(nameOne);
        names.add(name2);
        names.add(nameTwo);
	names.add(infoLabel);


        // Panels

        titlePanel = new JPanel();
        title = new JLabel("Connect 4 Game");
        titlePanel.setBackground(Color.GREEN);
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        numPanel = new JPanel();
        numPanel.setBackground(Color.WHITE);

        model = new ConnectFourModel();

        // Add components to the panel

        titlePanel.add(title);
        buttonPanel.add(restart);
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
        this.add(names, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(500,500));
        
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
	    if (text.contains("won") || text.contains("turn"))
	    {
	        infoLabel.setText(text);
	        for (int i = 1; i < num.length; i++)
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
    
    /** Enters the player for player 1
     */
    public void enterPlayer()
    {
        player1 = JOptionPane.showInputDialog("Enter your name: ");
    }
    
    /** Puts the chip in the desired column and sends signal to client that
     *    it is ready to drop a chip
     */
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == restart)
                pushRestart();
            if (event.getSource() == quit)
                System.exit(0);

            for (int i = 1; i < num.length; i++)
            {
                if (event.getSource() == num[i])
                {
                    column = i;
                    client.readyToDropChip(column);
                }
            }
    	}
    }
    
    /** Restarts the game
     */
    public void pushRestart()
    {
        JOptionPane.showMessageDialog(null, "Game has Reset!");

        JFrame frame = new JFrame("Connect Four");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ConnectFourClientGUI game = new ConnectFourClientGUI();
        frame.getContentPane().add(game);

        frame.pack();
        frame.setVisible(true);

    }

    /** Puts a chip in the specified column
     *  @param col the column that represents the pushed column number button
     */
    public void pushNum(int col)
    {
        if (model.isFull(col - 1))
	{
            JOptionPane.showMessageDialog(null, "Column is full, choose another");
	    num[col].setEnabled(false);
	}
        else
        {
            model.drop(col - 1);
            if (model.isWinner())
	    {
                JOptionPane.showMessageDialog(null, "Player " + model.getCurrentPlayer() + " has won!");
		for (int i = 0; i < num.length; i++)
			num[i].setEnabled(false);
	    }
            model.changePlayer();
        }

    }        
      
}

