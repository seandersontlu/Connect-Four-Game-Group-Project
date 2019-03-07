// *********************************
// ************COMPLETED************
// *********************************
//
// Sarah, Adrien, Montrel, Scott
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
    private static final int MAX_TURNS = 42;

    private boolean winner;
    private int totalMovesPlayed;
    private ConnectFourPlayer currentPlayer;
    private ConnectFourPlayer player1;
    private ConnectFourPlayer player2;
    private ConnectFourBoard board;

    /** Initializes the game
     */
    public ConnectFourGame()
    {
        totalMovesPlayed = 0;
        board = new ConnectFourBoard();
        player1 = new ConnectFourPlayer (new String());
        player2 = new ConnectFourPlayer (new String());
        currentPlayer = player1;
    }

    /** Returns player1
     */
    public ConnectFourPlayer getPlayer1()
    {
        return player1;
    }

    /** Returns player2
     */
    public ConnectFourPlayer getPlayer2()
    {
        return player2;
    }

    /** Returns the current player
     */
    public ConnectFourPlayer getCurrentPlayer()
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
     * @param name2 player 2's name
     * Sets the players names
     */
    public void setNames (String name1, String name2)
    {
        player1 = new ConnectFourPlayer (name1);
        player2 = new ConnectFourPlayer (name2);
    }

    /** Plays Connect Four
     */
    public void playGame(int col)
    {
        winner = false;
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
                winner = true;
                if (currentPlayer.equals(player1))
                    player1.addWin();
                else
                    player2.addWin();
                return;
            }
        }
    }

    /** Determines if someone has won yet
     */
    public boolean isWinner()
    {
        return winner;
    }

    /**
     * @param currentPlayer the current player
     * Switches turns by changing player assigned to currentPlayer
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
        totalMovesPlayed = 0;
        board = new ConnectFourBoard();
        currentPlayer = player1;
    }
}
            
