// Jordyn M, Coy T, David G
// CSCI 434 Project #1, Iteration #1
// ConnectFourGame.java
// 2-9-19
// 
// This program runs the game of connect four.

/**
 * This class plays the game, Connect Four
 * @author Sarah, Adriran, Scott, Montrel
 */

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

    /** Initializes the game
     */
    public ConnectFourGame()
    {
        totalMovesPlayed = 0;
        board = new ConnectFourBoard();
        setPlayer1Name (name1);
        setPlayer2Name (name2);
        currentPlayer = player1;
    }

    /** Returns player1
     */
    public Player getPlayer1()
    {
        return player1;
    }

    /** Returns player2
     */
    public Player getPlayer2()
    {
        return player2;
    }

    /** Returns the current player
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /** Returns the board
     */
    public ConnectFourBoard getBoard()
    {
        return board;
    }
    
    /**
     * @param name1  player 1's name
     * Sets player one's name and chip color
     */
    public void setPlayer1Name (String name1)
    {
        player1 = new Player (name1, ConnectFourBoard.YELLOW);
    }

    /**
     * @param name2 player 2's name
     * Sets player two's name and chip color
     */
    public void setPlayer2Name (String name2)
    {
        player2 = new Player (name2, ConnectFourBoard.RED);
    }

    /** Plays Connect Four
     */
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

    /**
     * @param currentPlayer the current player
     * Switches turns by changing player assigned to currentPlayer
     */
    public Player switchPlayer(Player currentPlayer)
    {
        if (currentPlayer == player1)
            return player2;
        else
            return player1;
    }

    /** Resets the game
    */
    public void resetGame()
    {
        name1 = "";
        name2 = "";
        totalMovesPlayed = 0;
        board = new ConnectFourBoard();
        currentPlayer = player1;
    }
}
            
