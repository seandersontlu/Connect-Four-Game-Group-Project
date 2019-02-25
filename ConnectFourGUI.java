//
//
//
//
//
//

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public ConnectFourGUI extend JFrame
{
    public static int WIDTH = 320;
    public static int HEIGHT = 320;
    public static int ROWS = 7;
    public static int COLUMNS = 6;
    
    
    public static void main (String[] args)
    {
        GameGUI window = new GameGUI();
        window.setVisible(true);
    }

        
    public ConnectFourGUI()
    {
        
        
        super("Game GUI");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         
        JRadioButton col1 = new JRadioButton("1");
        JRadioButton col2 = new JRadioButton("2");
        JRadioButton col3 = new JRadioButton("3");
        JRadioButton col4 = new JRadioButton("4");
        JRadioButton col5 = new JRadioButton("5");
        JRadioButton col6 = new JRadioButton("6");

        ButtonGroup colButtons = new ButtonGroup();

        colButtons.add(col1);
        colButtons.add(col2);
        colButtons.add(col3);
        colButtons.add(col4);
        colButtons.add(col5);
        colButtons.add(col6);

        ButtonListener buttonListener = 
            new ButtonListener();

        col1.addActionListener(buttonListener);
        col2.addActionListener(buttonListener);
        col3.addActionListener(buttonListener);
        col4.addActionListener(buttonListener);
        col5.addActionListener(buttonListener);
        col6.addActionListener(buttonListener);

        setLayout(new GridLayout(ROWS, COLUMNS))

        add(col1);
        add(col2);
        add(col3);
        add(col4);
        add(col5);
        add(col6);


        



        }

        PlayGame
        {

        }
