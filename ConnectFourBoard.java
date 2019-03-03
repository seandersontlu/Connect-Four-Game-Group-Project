// Jordyn Martin, Coy Tutt, David Glenewinkel
// CSCI 434 Project #1, Iteration #1 
// ConnectFourBoard.java
// 2/08/2019
//
// This is the class method that creates the board for the game
// connect four.

import java.util.Arrays;

enum Diagonal 
{
    LEFT, RIGHT;
}

public class ConnectFourBoard
{
    public static final int EMPTY = 0;
    public static final int YELLOW = 1;
    public static final int RED = 2;

    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    private int[][] gameBoard;

    public ConnectFourBoard()
    {
        // Will create board to play connect four with.
        gameBoard = new int[ROWS][COLUMNS];
        initializeBoard();
    }
    
    public int[][] getGameBoard()
    {
        return gameBoard;
    }

    public int getRowNum (int col)
    {
        int rowNum = 0;
        for (int row = ROWS - 1; row >= 0; row--)
            if (gameBoard[row][col] == EMPTY)
                rowNum = row;
        return rowNum;
    }
    
    public boolean isColumnFull(int col)
    {
        return gameBoard[0][col] != EMPTY;
    }

    private void initializeBoard()
    {
        for (int row = 0; row < ROWS; row++)
            Arrays.fill(gameBoard[row], EMPTY);
    }

    public void drop(int col, int color)
    {
        for (int row = ROWS - 1; row >= 0; row--)
            if (gameBoard[row][col] == EMPTY)
                gameBoard[row][col] = color;
    }
    
    public void resetBoard()
    {
        initializeBoard();
    }

    public boolean checkWinner()
    {
        return (verticalWin() || horizontalWin() 
            || diagonalWin(Diagonal.LEFT)
            || diagonalWin(Diagonal.RIGHT));
    }

    // Checking vertical
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

    // Checking horizontal
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

