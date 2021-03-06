// Adrian, Sarah, Montrell, Scott
// CSCI 434 Project 1
// 2/27/19
// ConnectFourConstants.java
// 
// This class is for the constants used for Connect Four.

/**
 * This class initializes the constants for the Connect Four's client-server
 * classes (ConnectFourClient, ConnectFourClientGui, ConnectFourServer, and
 * ConnectFourSession)
 * @author Jordyn Martin, Coy Tutt, David Glenewinkel II
 * @author Sarah, Adriran, Scott, Montrel
 */

public interface ConnectFourConstants
{
    // Messages for the session and Client
    public static final int START = 0;
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;
    public static final int PLAYER1_WON = 1;
    public static final int PLAYER2_WON = 2;
    public static final int CONTINUE = 3;
    public static final int WAIT_FOR_PLAYER = 4;
    public static final int MAKE_MOVE = 7;

    // Used for end of game
    public static final int CONNECT_FOUR = 2;
    public static final int MAX_MOVES = 42;

  
    // Port number
    public static final int PORT = 31524; 
}
