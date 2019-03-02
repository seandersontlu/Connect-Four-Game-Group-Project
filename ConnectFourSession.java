

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

		}
	}
}

