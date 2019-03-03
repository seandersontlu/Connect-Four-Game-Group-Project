// Jordyn M, Coy T, David G
// CSCI 434 Project #1, Iteration #1
// ConnectFourGame.java
// 2-9-19
// 
// This program runs the game of connect four.

public class ConnectFourGame
{
    public static final int MAX_TURNS = 42;
    
    String name1 = "";
    String name2 = "";
    int totalMovesPlayed;
    Player currentPlayer;
    Player player1;
    Player player2;
    ConnectFourBoard board;

    public ConnectFourGame()
    {
        totalMovesPlayed = 0;
        board = new ConnectFourBoard();
        setPlayer1Name (name1);
        setPlayer2Name (name2);
        currentPlayer = player1;
    }

    private Player getPlayer1()
    {
        return player1;
    }
    public Player getPlayer2()
    {
        return player2;
    }
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }
    public ConnectFourBoard getBoard()
    {
        return board;
    }
    
    public void setPlayer1Name (String name1)
    {
        player1 = new Player (name1, ConnectFourBoard.YELLOW);
    }

    public void setPlayer2Name (String name2)
    {
        player2 = new Player (name2, ConnectFourBoard.RED);
    }

    public void playGame(int col)
    {
        while (true && totalMovesPlayed < MAX_TURNS)
        {
            if (!board.isColumnFull(col))
                board.drop(col, currentPlayer.getColor());
            totalMovesPlayed++;
            
            if (board.checkWinner())
            {
                if (currentPlayer.equals(player1))
                    player1.addWin();
                else
                    player2.addWin();
                return;
            }
            currentPlayer = switchPlayer(currentPlayer);
        }
    }

    public Player switchPlayer(Player currentPlayer)
    {
        if (currentPlayer == player1)
            return player2;
        else
            return player1;
    }

    public void resetGame()
    {
        name1 = "";
        name2 = "";
        totalMovesPlayed = 0;
        board = new ConnectFourBoard();
        currentPlayer = player1;
    }
}
            
