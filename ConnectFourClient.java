// ConnectFourClient.java

import java.io.*;
import java.net.*;
import java.util.Observable;

public class ConnectFourClient extends Observable implements ConnectFourConstants, Runnable
{
    private static final String SERVER = "localhost";
    
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private int playerNum;
    private String playerName;
    private boolean waiting;
    private boolean continuePlaying;
    
    public ConnectFourClient()
    {
        Thread myThread = new Thread(this);
        myThread.start();
    }
    
    public void run()
    {
        waiting = true;
        continuePlaying = true;

        try
        {
            Socket server = new Socket (SERVER, PORT);
            fromServer = new DataInputStream (server.getInputStream());
            toServer = new DataOutputStream (server.getOutputStream());
            
            playerName = fromServer.readUTF();
            playerNum = fromServer.readInt();
            setChanged();
            notifyObservers (playerName + " is player " + playerNum);

            fromServer.readInt();

            do
            {
                if (playerNum == PLAYER_ONE)
                {
                    waitForPlayerAction();
                    takeTurn();
                    if (!continuePlaying)
                        break;
                    receiveInformationFromServer();
                }
                else
                {
                    receiveInformationFromServer();
                    if (!continuePlaying)
                        break;
                    waitForPlayerAction();
                    takeTurn();
                }
            } while (continuePlaying);

            server.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    private void waitForPlayerAction() throws InterruptedException
    {
        waiting = true;
        while (waiting)
            Thread.sleep(100);
        waiting = true;
    }
    
    public void readyToDropChip()
    {
        waiting = false;
    }

    private void takeTurn() throws IOException
    {
        toServer.writeInt(MAKE_MOVE);
        int colNum = fromServer.readInt();
        setChanged();
        notifyObservers(new Integer(colNum));

        int status = fromServer.readInt();
        System.out.println(status);
        
        if (status == PLAYER1_WON)
        {
            System.out.println(playerName + " won!!");
            setChanged();
            notifyObservers (new String("END OF GAME: " + playerName + " won"));
            continuePlaying = false;
        }
        else if (status == PLAYER2_WON)
        {
            System.out.println(playerName + " won!!");
            setChanged();
            notifyObservers (new String("END OF GAME: " + playerName + " won"));
            continuePlaying = false;
        }
    }

    private void receiveInformationFromServer() throws IOException
    {
        int status = fromServer.readInt();

        if (status == PLAYER1_WON)
        {
            if (playerNum == 2)
            {
                int colNum = fromServer.readInt();
                setChanged();
                notifyObservers(new Integer(colNum));

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
                int colNum = fromServer.readInt();
                setChanged();
                notifyObservers(new Integer(colNum));

            }
            System.out.println("Player 2 won!");
            setChanged();
            notifyObservers(new String("END OF GAME: Player 2 won!"));
            continuePlaying = false;
        }
        else
        {
            int colNum = fromServer.readInt();
            setChanged();
            notifyObservers(new Integer(colNum));
        }
    }

}
