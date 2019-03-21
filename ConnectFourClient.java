// Montrell, Adrian, Sarah, Scott
// CSCI 434 Project #1, Iteration #3
// ConnectFourClient.java
// 3/21/2019
//
// This class represents the client side of a Connect Four game.

import java.io.*;
import java.net.*;
import java.util.Observable;

/**
 * This class represents the client side for a Connect Four game
 * @author Adrian, Scott, Montrell, Sarah
 */

public class ConnectFourClient extends Observable implements ConnectFourConstants, Runnable
{
    private static final String SERVER = "localhost";
    
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private ConnectFourPlayer player;
    private int playerNum;
    private String playerName;
    private boolean waiting;
    private boolean continuePlaying;
    private int col = 0;
    private ConnectFourBoard board;

    public ConnectFourClient()
    {
        Thread myThread = new Thread(this);
        myThread.start();
    }
    
    public void run()
    {
	player = new ConnectFourPlayer("null");	
	board = new ConnectFourBoard();
        waiting = true;
        continuePlaying = true;

        try
        {
            Socket server = new Socket(SERVER, PORT);
            fromServer = new DataInputStream(server.getInputStream());
            toServer = new DataOutputStream(server.getOutputStream());
            
            playerNum = fromServer.readInt();
            setChanged();
            if (playerNum == 1)
                notifyObservers ("You are yellow");
            else 
                notifyObservers ("You are red");
            player = new ConnectFourPlayer("Player " + playerNum);
	    
	        // Get START notification from Server
            fromServer.readInt();

            do
            {
                if (playerNum == PLAYER_ONE)
                {
		            System.out.println("Player One: ");
                        waitForPlayerAction();
		            System.out.println("Waited for player 1");
                        takeTurn();
		            System.out.println("Took player 1 turn");
                        receiveInformationFromServer();
		            System.out.println("Took player 1 turn\n");
                }
                else
                {
		            System.out.println("Player Two: ");
                        receiveInformationFromServer();
		            System.out.println("Player 2 recieved information from Server");
                    if (!continuePlaying)
                        break;
                    waitForPlayerAction();
		            System.out.println("Waited for player 2 action");
                    takeTurn();
		            System.out.println("Took player 2 turn\n");
                }
            } while (continuePlaying);
        }
        catch (Exception e)
        {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /** 
     * Puts a thread to sleep and changes the waiting to true
     * @throws InterruptedException if a thread is interrupted
     */
    private void waitForPlayerAction() throws InterruptedException
    {
        while (waiting)
	    {
            Thread.sleep(100);
	    }
        waiting = true;
    }
    
    /** Changes the waiting to false
     */
    public void readyToDropChip(int column)
    {
        waiting = false;
	    this.col = column;
    }

    /** Sends a signal to the session, recieves a response from server, and  checks for a win
     * @throws IOException if the return from the server is not an integer
     */
    private void takeTurn() throws IOException
    {
        toServer.writeInt(MAKE_MOVE);
	    toServer.writeInt(col);
        int column = fromServer.readInt();
	    System.out.println("The column number recieved is " + column);
        setChanged();
        notifyObservers(new Integer(column));
	
	    // See if this turn won the game
        int status = fromServer.readInt();
	    System.out.println("The status number recieved is " + status);

        if (status == PLAYER1_WON)
        {
            System.out.println("Player " + playerNum + " won!!");
            setChanged();
            notifyObservers (new String("END OF GAME: " + playerNum + " won"));
            continuePlaying = false;
        }
        else if (status == PLAYER2_WON)
        {
            System.out.println("Player " + playerNum + " won!!");
            setChanged();
            notifyObservers (new String("END OF GAME: " + playerNum + " won"));
            continuePlaying = false;
        } 
    }

    /** Recieves the information from the server, and sends out the appropriate
     *      messages.
     * @throws IOException if the response from the server is not an integer
     */
    private void receiveInformationFromServer() throws IOException
    {
        int status = fromServer.readInt();
	    System.out.println("Status: " + status);
        if (status == PLAYER1_WON)
        {
            if (playerNum == 2)
            {
		        int column = fromServer.readInt();
		        setChanged();
		        notifyObservers(new Integer(column));
            }
            System.out.println("Player 1 won!");
            setChanged();
            notifyObservers(new String("END OF GAME: Player 1 won!"));
            continuePlaying = false;
        }
        else if (status == PLAYER2_WON)
        {
            if (playerNum == 1)
            {
		        int column = fromServer.readInt();
		        setChanged();
		        notifyObservers(new Integer(column));
            }
            System.out.println("Player 2 won!");
            setChanged();
            notifyObservers(new String("END OF GAME: Player 2 won!"));
            continuePlaying = false;
        }
        else
        {
	        int column = fromServer.readInt();
            setChanged();
	        notifyObservers(new Integer(column));
        }
    }
}
