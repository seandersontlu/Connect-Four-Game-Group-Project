// ConnectFourClient.java

import java.io.*;
import java.net.Socket;
import java.util.Observable;

public class ConnectFourClient extends Observable implements ConnectFourConstants, Runnable
{
    private static final String SERVER = "localhost";
    
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private int playerName;
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
            
            playerName = fromServer.readString();
            setChanged();
            notifyObservers ("Player " + playerName);

            fromServer.readInt();

            do
            {
                if (playerNum == PLAYER1)
                {
                    waitForPlayerAction();
                    takeTurn();
                    if (!continuePlaying)
                        break;
                    recieveInformationFromServer();
                }
                else
                {
                    recieveInformationFromServer();
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
        while (waiting)
            Thread.sleep(100);
        waiting = true;
    }
    
    public void readyToDropChip()
    {
        waiting = false;
    }

    private void takeTurn()
    {
        toServer.writeInt(MAKE_MOVE);
    }

    private void recieveInformationFromServer() throws IOException
    {
    }
}
