// *********************************
// ************COMPLETED************
// *********************************
//
// Sarah, Adrien, Montrell, Scott
// CSCI 434 Project #1, Iteration #3 
// ConnectFourBoard.java
// 3/06/2019
//
// This is the class method that creates the board for the game
// connect four.

import java.util.Arrays;

enum Diagonal 
{
    LEFT, RIGHT;
}

/**
 * This class represents a connect four board
 * @author Jordyn Martin, Coy Tutt, David Glenewinkel II
 * @author Sarah, Adrian, Scott, Montrell
 */
public class ConnectFourBoard implements ConnectFourConstants
{
    public static final int EMPTY = 0;
    public static final int YELLOW = 1;
    public static final int RED = 2;

    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    
    private int[][] gameBoard;
    private ConnectFourPlayer player1;
    private ConnectFourPlayer player2;
    private int currentPlayer;


    /** Creates the connect four board
     */
    public ConnectFourBoard(ConnectFourPlayer player1, ConnectFourPlayer player2)
    {
        gameBoard = new int[ROWS][COLUMNS];
	this.player1 = player1;
	this.player2 = player2;
	currentPlayer = PLAYER_ONE;
        initializeBoard();
    }
    
    /** Returns the board
     */
    public int[][] getGameBoard()
    {
        return gameBoard;
    }

    /**
     * @param col   the column number
     * Returns the empty row for the given column
     */
    public int getRowNum (int col)
    {
        int rowNum = 0;
        for (int row = ROWS - 1; row >= 0; row--)
            if (gameBoard[row][col] == EMPTY)
                rowNum = row;
        return rowNum;
    }
    
    /**
     * Returns the current player as an int for this turn
     */
    public int getCurrentPlayer()
    {
	int result = PLAYER_ONE;
	if (currentPlayer == PLAYER_ONE)
	    ;
	else
		result = PLAYER_TWO;
	return result;
    }

    /**
     * Returns the current player name for this turn
     */
    public String getCurrentPlayerName()
    {
	String result = "";
	if (currentPlayer == PLAYER_ONE)
		result = player1.getName();
	else
		result = player2.getName();
	return result;
    }
    
    /**
     * @param col   the column number
     * Checks if a column is full
     */
    public boolean isColumnFull(int col)
    {
        return gameBoard[0][col] != EMPTY;
    }

    /** Initializes the board with the EMPTY constant
     */
    private void initializeBoard()
    {
        for (int row = 0; row < ROWS; row++)
            Arrays.fill(gameBoard[row], EMPTY);
    }

    /**
     * @param col   the column number
     * @param color the integer value for the chip color
     * adds chip to the given column and then switches
     * the current player
     */
    public void drop(int col, int color)
    {
        for (int row = ROWS - 1; row >= 0; row--)
            if (gameBoard[row][col] == EMPTY)
                gameBoard[row][col] = color;
	switchPlayer();
    }

    /**
     * Switches the current player
     */
    public void switchPlayer()
    {
	if (currentPlayer == PLAYER_ONE)
		currentPlayer = PLAYER_TWO;
	else
		currentPlayer = PLAYER_ONE;
    }
    
    /** Resets the board
     */
    public void resetBoard()
    {
        initializeBoard();
    }

    /** Checks for vertical, horizontal, left diagonal, and right diagonal wins
     */
    public boolean checkWinner()
    {
        return (verticalWin() || horizontalWin() 
            || diagonalWin(Diagonal.LEFT)
            || diagonalWin(Diagonal.RIGHT));
    }

    /**
     * Checks if the board is full
     */
    public boolean isFull()
    {
	boolean result = true;
	for (int cols = 0; cols < COLUMNS; cols++)
	{
	    if (!isColumnFull(cols))
		result = false;
	}

	return result;
    }


    /** Checks for a vertical win
     */
    private boolean verticalWin()
    {
        boolean result = false;
    
        for (int col = 0; col < COLUMNS; col++)
        {
            // subtracting 3 so the for loop doesn't need to go through more
            // than it needs to.
            for (int row = 0; row < ROWS - 3; row++)
            {
                if (gameBoard[row][col] != ' ' && 
                    gameBoard[row][col] == gameBoard[row+1][col] && 
                    gameBoard[row][col] == gameBoard[row+2][col] && 
                    gameBoard[row][col] == gameBoard[row+3][col])
                    result = true;
            }
        }
        return result;
    }

    /** Checks for a horizontal win
     */
    private boolean horizontalWin()
    {
        boolean result = false;

        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS - 3; col++)
            {
                if (gameBoard[row][col] != ' ' && 
                    gameBoard[row][col] == gameBoard[row][col+1] && 
                    gameBoard[row][col] == gameBoard[row][col+2] && 
                    gameBoard[row][col] == gameBoard[row][col+3])
                    result = true;
            }
        }
        return result;
    }

    /** Checks for a diagonal win from left and right
     */
    private boolean diagonalWin(Diagonal direction)
    {
        boolean result = false;

        // Checking left diagonal
        if (direction == Diagonal.LEFT)
        {
            for (int row = 0; row < ROWS - 3; row++)
            {
                for (int col = 0; col < COLUMNS - 3; col++)
                {
                    if (gameBoard[row][col] != ' ' && 
                        gameBoard[row][col] == gameBoard[row+1][col+1] && 
                        gameBoard[row][col] == gameBoard[row+2][col+2] && 
                        gameBoard[row][col] == gameBoard[row+3][col+3])
                        result = true;
                }
            }
        }
        // Checking right diagonal
        else
        {
            for (int row = 0; row < ROWS - 3; row++)
            {
                for (int col = 3; col < COLUMNS; col++)
                {
                    if (gameBoard[row][col] != ' ' && 
                        gameBoard[row][col] == gameBoard[row+1][col-1] && 
                        gameBoard[row][col] == gameBoard[row+2][col-2] && 
                        gameBoard[row][col] == gameBoard[row+3][col-3])
                        result = true;
                }
            }
        }

        return result;
    }

}

