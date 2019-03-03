
import java.io.*;
import java.net.*;


public class ConnectFourServer implements ConnectFourConstants
{
	public static void main(String[] args)
	{
		new ConnectFourServer();
	}


	public ConnectFourServer()
	{

		try
		{
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("The server is up and running...");

			int sessionNum = 1;

			while(true)
			{
				// Listen for player1
				Socket player1 = serverSocket.accept();
				System.out.println("Player 1 has joined session " + sessionNum);

				DataOutputStream player1Stream = 
					new DataOutputStream(player1.getOutputStream());
				player1Stream.writeInt(PLAYER_ONE);

				// Listen for player2
				Socket player2 = serverSocket.accept();
				System.out.println("Player 2 has joined session " + sessionNum);
				DataOutputStream player2Stream = 
					new DataOutputStream(player2.getOutputStream());
				player2Stream.writeInt(PLAYER_TWO);
				
				// Create a session
				ConnectFourSession task = new ConnectFourSession(player1, player2);
				new Thread(task).start();

				sessionNum++;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}


