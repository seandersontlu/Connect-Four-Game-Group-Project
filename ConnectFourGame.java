// Sarah, Adrian, Montrell, Scott
// CSCI 434 Project #1, Iteration #3
// ConnectFourGame.java
// 3-6-19
// 
// This program runs the game of connect four.

/**
 * This class plays the game, Connect Four
 * @author Jordyn Martin, Coy Tutt, David Glenewinkel II
 * @author Sarah, Adriran, Scott, Montrel
 */

public class ConnectFourGame
{
    public static final int MAX_TURNS = 42;
    
    String name1 = "";
    String name2 = "";
    int totalMovesPlayed;
    ConnectFourBoard board;
    ConnectFourPlayer currentPlayer;
    ConnectFourPlayer player1;
    ConnectFourPlayer player2;

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
     *  @return player1
     */
    public ConnectFourPlayer getPlayer1()
    {
        return player1;
    }

    /** Returns player2
     *  @return player2
     */
    public ConnectFourPlayer getPlayer2()
    {
        return player2;
    }

    /** Returns the current player
     *  @return the current player
     */
    public ConnectFourPlayer getCurrentPlayer()
    {
        return currentPlayer;
    }

    
    /**
     * @param name1  player 1's name
     * Sets player one's name
     */
    public void setPlayer1Name (String name1)
    {
        player1 = new ConnectFourPlayer (name1);
    }

    /**
     * @param name2 player 2's name
     * Sets player two's name
     */
    public void setPlayer2Name (String name2)
    {
        player2 = new ConnectFourPlayer (name2);
    }

    /** Plays Connect Four
     *  @param col is the column number
     */
    public void playGame(int col)
    {
        while (true && totalMovesPlayed < MAX_TURNS)
        {
            if (!board.isColumnFull(col)) 
            {
                if (currentPlayer == player1)
                    board.drop(col, ConnectFourBoard.YELLOW);
                else
                    board.drop(col, ConnectFourBoard.RED);
                totalMovesPlayed++;
            }
            
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
     * Switches turns by changing player assigned to currentPlayer
     * @return the switched player
     * @param currentPlayer the current player
     */
    public ConnectFourPlayer switchPlayer(ConnectFourPlayer currentPlayer)
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
            
