

import java.io.*;
import java.net.*;

public class ConnectFourSession implements ConnectFourConstants, Runnable
{
	private Socket player1;
	private Socket player2;

	public ConnectFourSession(Socket player1, Socket player2)
	{
		this.player1 = player1;
		this.player2 = player2;
	}

	public void run()
	{
	    try
	    {
		DataInputStream fromPlayer1
			= new DataInputStream(player1.getInputStream());
		DataOutputStream toPlayer1
			= new DataOutputStream(player1.getOutputStream());
		DataInputStream fromPlayer2
			= new DataInputStream(player2.getInputStream());
		DataOutputStream toPlayer2
			= new DataOutputStream(player2.getOutputStream());

		toPlayer1.writeInt(START);
		toPlayer2.writeInt(START);

		// Need to be able to get names of each player  
		// to make board. Dummy player names 
		// are used here for the meantime

		String name1 = "red";
		String name2 = "yellow";

		ConnectFourPlayer playerOne = new ConnectFourPlayer(name1);
		ConnectFourPlayer playerTwo = new ConnectFourPlayer(name2);

		ConnectFourBoard board = new ConnectFourBoard(playerOne, playerTwo);

         
            	while (true)
            	{
                    // Player 1 makes a move
                    fromPlayer1.readInt();
                    int col = fromPlayer1.readInt();
                    sendMove(toPlayer1, col);

                    if (board.checkWinner())
                    {
                        if (board.getCurrentPlayer() == PLAYER_ONE)
                        {
                            toPlayer1.writeInt(PLAYER1_WON);
                            toPlayer2.writeInt(PLAYER2_LOSS);
                    	}
                    	else
                    	{
                            toPlayer2.writeInt(PLAYER2_WON);
                            toPlayer1.writeInt(PLAYER1_LOSS);
                        }
                    	    sendMove(toPlayer2, col);
                    	    break;
                	}
                	else if (board.isFull())
                	{
                    	    toPlayer1.writeInt(MAX_MOVES);
                    	    toPlayer2.writeInt(MAX_MOVES);

                    	    sendMove(toPlayer2, col);
                    	    break;
                	}
                	else
                	{
                    	    //Notify player 2 to take turn
                    	    toPlayer2.writeInt(CONTINUE);
                    	    toPlayer1.writeInt(WAIT_FOR_PLAYER);

                    	    sendMove(toPlayer2, col);
                	}
                
                    // Handle Player 2 roll
                    fromPlayer2.readInt();
                    col = fromPlayer2.readInt();
                    sendMove(toPlayer2, col);

                    if (board.checkWinner())
                    {
                    	if (board.getCurrentPlayer() == PLAYER_ONE)
                    	{
                            toPlayer1.writeInt(PLAYER1_WON);
                            toPlayer2.writeInt(PLAYER2_LOSS);
                    	}
                    	else
                    	{
                            toPlayer2.writeInt(PLAYER2_WON);
                            toPlayer1.writeInt(PLAYER1_LOSS);
                    	}
                    	sendMove(toPlayer1, col);
                    	break;
                    }
                    else if (board.isFull())
                    {
                        toPlayer1.writeInt(MAX_MOVES);
                        toPlayer2.writeInt(MAX_MOVES);

                        sendMove(toPlayer1, col);
                        break;
                    }
                    else
                    {
                        //Notify player 2 to take turn
                        toPlayer1.writeInt(CONTINUE);
                        toPlayer2.writeInt(WAIT_FOR_PLAYER);

                        sendMove(toPlayer1, col);
                    }

            	} 
                player1.close();
                player2.close();
                
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    private void sendMove(DataOutputStream out, int col)
        throws IOException
    {
       out.writeInt(col);
    }
}

