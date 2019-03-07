// Coy T, Jordyn M, David G
// CSCI 434 Project #1, Iteration #2
// ConnectFourClientGUI.java
// 2-18-19
//
// This will create the frame for the Connect four game.
/*
    // Did not use object oriented design.
    // No use of player or connect four objects.
    // Missing code for ConnectFourGui
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class ConnectFourClientGUI extends JFrame implements ConnectFourConstants, Observer
{
    public static final String server = "localhost";

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private String player1;
    private String player2;
    private JLabel[][] slot;
    private JLabel title;
    private JButton start;
    private JButton restart;
    private JButton quit;
    private JButton[] num;
    private JPanel buttonPanel;
    private JPanel numPanel;
    private JPanel titlePanel;
    private JFrame frame;
    
    private JLabel infoLabel;
    private ConnectFourClient client;
    private boolean waitingForPlayer1;

    public static void main(String[] args)
    {
	    ConnectFourClientGUI window = new ConnectFourClientGUI();
	    window.pack();
	    window.setVisible(true);
    }

    public ConnectFourClientGUI()
    {
	frame = new JFrame("Connect Four");

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        
        infoLabel = new JLabel(" ");
        p2.add(infoLabel, BorderLayout.SOUTH);
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
        names.add(name1);
        names.add(nameOne);
        names.add(name2);
        names.add(nameTwo);


        // Panels

        titlePanel = new JPanel();
        title = new JLabel("Connect 4 Game");
        titlePanel.setBackground(Color.GREEN);
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        numPanel = new JPanel();
        numPanel.setBackground(Color.WHITE);

	// Middle panel where the board is
	setLayout(new GridLayout(ROWS, COLUMNS));
	slot = new JLabel[ROWS][COLUMNS];
	
	
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS; col++)
            {
                slot[row][col] = new JLabel();
                slot[row][col].setFont(new Font("SansSerif", Font.BOLD, 40));
                slot[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                slot[row][col].setBorder(new LineBorder(Color.BLACK));
                add(slot[row][col]);
            }
        }

	setSize(700,600);
	setVisible(true);


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
        //this.add(slot, BorderLayout.CENTER);
        this.add(names, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(500,500));
        
        waitingForPlayer1 = true;
        client = new ConnectFourClient();
        client.addObserver(this);
    }


    public void update(Observable o, Object obj)
    {
        if (obj instanceof String)
        {
            String text = (String) obj;
            if (text.contains("won"));
            {
                infoLabel.setText(text);
                for (int i = 0; i < num.length; i++)
                    num[i].setEnabled(false);
            }
        }
        else if (obj instanceof Integer)
        {
            int col = (Integer) obj;

            if (waitingForPlayer1)
                infoLabel.setText("It is player 1's turn.");
            else
                infoLabel.setText("It is player 2's turn.");

            //model.drop(col);
        }
    }

    public void enterPlayer()
    {
        player1 = JOptionPane.showInputDialog("Enter your name: ");
        //player2 = JOptionPane.showInputDialog("Player 2 enter your name: ");
    }

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
                    client.readyToDropChip();
                }
            }
    	}
    }

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
}

