// Jordyn M, Coy T, David G
// CSCI 434 Project #1, Iteration 2
// ConnectFourGui.java
// 2-18-19
//
// Creates the Connect Four GUI

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

enum Diagonal 
{
    LEFT, RIGHT;
}

public class ConnectFourGui extends JPanel
{
    private JLabel[][] slot;
    private int[][] gameBoard;
    private int currentPlayer;
    private final static int PLAYER_ONE = 1;
    private final static int PLAYER_TWO = 2;
    private final static int EMPTY = 0;

    
    private final static ImageIcon PLAYER1_CHIP = new ImageIcon("Y_Player.png");
    private final static ImageIcon PLAYER2_CHIP = new ImageIcon("R_Player.png");

    private final static int ROWS = 6;
    private final static int COLUMNS = 7;

    public ConnectFourGui()
    {
        currentPlayer = PLAYER_ONE;

        // Create the game board, or grid in this case. LOGICAL

        gameBoard = new int[ROWS][COLUMNS];

        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS; col++)
            {
                gameBoard[row][col] = EMPTY;
            }
        }

        // Create the game board, or grid in this case. GUI

        setLayout(new GridLayout(ROWS, COLUMNS));
        slot = new JLabel[ROWS][COLUMNS];
        
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS; col++)
            {
                slot[row][col] = new JLabel();
                slot[row][col].setFont(new Font("SansSerif", Font.BOLD, 40));
                slot[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                slot[row][col].setBorder(new LineBorder(Color.BLACK));
                add(slot[row][col]);
            }
        }

        setSize(700,600);
        setVisible(true);
    }
    
    public int getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void changePlayer()
    {
        currentPlayer = (currentPlayer%2)+1;
    }

    public void drop(int col)
    {
        for (int row = ROWS - 1; row > -1; row--)
        {
            if (gameBoard[row][col] == EMPTY)
            {
                gameBoard[row][col] = currentPlayer;
                draw(col, row);
                break;
            }
        }
    }
    
    public void draw(int col, int row)
    {
        if (currentPlayer == PLAYER_ONE)
        {
            slot[row][col].setIcon(PLAYER1_CHIP);
        }
        else
        {
            slot[row][col].setIcon(PLAYER2_CHIP);
        }
    }
    
    public boolean isFull(int col)
    {
        return gameBoard[0][col] != EMPTY;
    }
    
    public boolean isfullBoard()
    {
        boolean full = true;
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                if (gameBoard[i][j] == EMPTY)
                    full = false;

        return full;
    }

    public boolean isWinner()
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
                if (gameBoard[row][col] != 0 && gameBoard[row][col] ==
                    gameBoard[row+1][col] && gameBoard[row][col] ==
                    gameBoard[row+2][col] && gameBoard[row][col] ==
                    gameBoard[row+3][col])
                {
                    result = true;
                }
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
                if (gameBoard[row][col] != 0 && gameBoard[row][col] ==
                    gameBoard[row][col+1] && gameBoard[row][col] ==
                    gameBoard[row][col+2] && gameBoard[row][col] ==
                    gameBoard[row][col+3])
                {
                    result = true;
                }
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
                    if (gameBoard[row][col] != 0 && gameBoard[row][col] ==
                        gameBoard[row+1][col+1] && gameBoard[row][col] ==
                        gameBoard[row+2][col+2] && gameBoard[row][col] ==
                        gameBoard[row+3][col+3])
                    {
                        result = true;
                    }
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
                    if (gameBoard[row][col] != 0 && gameBoard[row][col] ==
                        gameBoard[row+1][col-1] && gameBoard[row][col] ==
                        gameBoard[row+2][col-2] && gameBoard[row][col] ==
                        gameBoard[row+3][col-3])
                    {
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}     

