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
public class ConnectFourBoard
{
    public static final int EMPTY = 0;
    public static final int YELLOW = 1;
    public static final int RED = 2;

    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    private int[][] gameBoard;

    /** Creates the connect four board
     */
    public ConnectFourBoard()
    {
        gameBoard = new int[ROWS][COLUMNS];
        initializeBoard();
    }
    
    /** Returns the board
     *  @return will return the integer version of the gameboard
     */
    public int[][] getGameBoard()
    {
        return gameBoard;
    }

    /**
     * Returns the empty row for the given column
     * @param col   the column number
     * @return the row number
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
     * Returns the value of the given slot
     * @param col the column number
     * @return the value in the given slot
     */
    public int getSlot(int col)
    {
         return gameBoard[getRowNum(col)][col];
    }
    
    /**
     * Checks if a column is full
     * @param col   the column number
     * @return determines if the column is full
     */
    public boolean isColumnFull(int col)
    {
        return gameBoard[0][col] != EMPTY;
    }
	
    /**
     * Returns if the board is empty
     * @return determines if the board is full
     */
    public boolean isBoardFull()
    {
        boolean full = false;
	for (int row = 0; row < ROWS; row++)
	    for (int col = 0; col < COLUMNS; col++)
	        if (gameBoard[row][col] != EMPTY)
		    full = false;
	
	return full;
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
     * adds chip to the given column
     */
    public void drop(int col, int color)
    {
        for (int row = ROWS - 1; row >= 0; row--)
            if (gameBoard[row][col] == EMPTY)
                gameBoard[row][col] = color;
    }
    
    /** Resets the board
     */
    public void resetBoard()
    {
        initializeBoard();
    }

    /** Checks for vertical, horizontal, left diagonal, and right diagonal wins
     * @return if there is a winner
     */
    public boolean checkWinner()
    {
        return (verticalWin() || horizontalWin() 
            || diagonalWin(Diagonal.LEFT));
    }

    /** Checks for a vertical win
     *  @return determines if there is a win vertically
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
                if (gameBoard[row][col] != EMPTY && 
                    gameBoard[row][col] == gameBoard[row+1][col] && 
                    gameBoard[row][col] == gameBoard[row+2][col] && 
                    gameBoard[row][col] == gameBoard[row+3][col])
                    result = true;
            }
        }
        return result;
    }

    /** Checks for a horizontal win
     *  @return determines if there is a win horizontally
     */
    private boolean horizontalWin()
    {
        boolean result = false;

        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS - 3; col++)
            {
                if (gameBoard[row][col] != EMPTY && 
                    gameBoard[row][col] == gameBoard[row][col+1] && 
                    gameBoard[row][col] == gameBoard[row][col+2] && 
                    gameBoard[row][col] == gameBoard[row][col+3])
                    result = true;
            }
        }
        return result;
    }

    /** 
     * Checks for a diagonal win from left and right
     * @param direction the direction of the diagonal
     * @return determines if there is a win diagonally
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
                    if (gameBoard[row][col] != EMPTY && 
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
                    if (gameBoard[row][col] != EMPTY && 
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

