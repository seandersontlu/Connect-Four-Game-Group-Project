// Coy T, Jordyn M, David G
// CSCI 434 Project #1, Iteration #2
// ConnectFourFrame.java
// 2-18-19
//
// This will create the frame for the Connect four game.

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class ConnectFourFrame extends JPanel
{
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

    private ConnectFourGui connectFourPanel;

    public ConnectFourFrame()
    {
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

        connectFourPanel = new ConnectFourGui();

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
        this.add(connectFourPanel, BorderLayout.CENTER);
        this.add(names, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(500,500));
    }
    
    public void enterPlayer()
    {
        player1 = JOptionPane.showInputDialog("Player 1 enter your name: ");
        player2 = JOptionPane.showInputDialog("Player 2 enter your name: ");
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
                    pushNum(i);
            }
        }
    }

    public void pushRestart()
    {
        JOptionPane.showMessageDialog(null, "Game has Reset!");

        JFrame frame = new JFrame("Connect Four");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ConnectFourFrame game = new ConnectFourFrame();
        frame.getContentPane().add(game);

        frame.pack();
        frame.setVisible(true);

    }

    public void pushNum(int col)
    {
        if (connectFourPanel.isFull(col - 1))
            JOptionPane.showMessageDialog(null, "Column is full, choose another");
        else
        {
            connectFourPanel.drop(col-1);
            if (connectFourPanel.isWinner())
                JOptionPane.showMessageDialog(null, "Player " + connectFourPanel.getCurrentPlayer() + " has won!");
            connectFourPanel.changePlayer();
        }
    }
}

