// David G, Coy T, Jordyn M
// CSCI 434 Project #1, Iteration #2
// RunConnectFour.java
// 2-19-19
//
// This will run the Connect Four game.

import java.awt.*;
import javax.swing.*;

public class RunConnectFour
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Connect Four");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ConnectFourFrame game = new ConnectFourFrame();
        frame.getContentPane().add(game);

        frame.pack();
        frame.setVisible(true);
    }
}
