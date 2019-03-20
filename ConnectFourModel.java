// Jordyn M, Coy T, David G
// CSCI 434 Project #1, Iteration 2
// ConnectFourModel.java
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

/** This class represents a model for the Client GUI.
 *  @author Jordyn M, Coy T, David G
 *  @author Scott, Sarah, Montrell, Adrian
 */
public class ConnectFourModel extends JPanel
{
    private JLabel[][] slot;
    private int[][] gameBoard;
    private int currentPlayer;
    private final static int PLAYER_ONE = 1;
    private final static int PLAYER_TWO = 2;
    private final static int EMPTY = 0;

    
    private final static ImageIcon PLAYER1_CHIP = new ImageIcon("Y_Player.png");
    private final static ImageIcon PLAYER2_CHIP = new ImageIcon("R_Player.png");

    public final static int ROWS = 6;
    private final static int COLUMNS = 7;

    public ConnectFourModel()
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

    }
    
    /** Returns the number for the current player
     *  @return the current player
     */
    public int getCurrentPlayer()
    {
        return currentPlayer;
    }

    /** Changes who the current player is
     */
    public void changePlayer()
    {
        currentPlayer = (currentPlayer%2)+1;
    }

    /** Puts the chip in the specified column
     *  @param col is the column number to insert the chip into
     */
    public void drop(int col)
    {
        for (int row = ROWS - 1; row > 0; row--)
        {
            if (gameBoard[row][col] == EMPTY)
            {
                gameBoard[row][col] = currentPlayer;
                draw(col, row);
                break;
            }
        }
    }
    
    /** Set the correct slot to show the correct image based on the player
     *  @param col is the column number
     *  @param row is the row number
     */
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
    
    /** Checks if the slot is full
     *  @param col is the column number to check
     *  @return wheter the slot is full or not
     */
    public boolean isFull(int col)
    {
        return gameBoard[0][col] != EMPTY;
    }
    
    /** Checks if the board is full
     *  @return if the board is full
     */
    public boolean isFullBoard()
    {
        boolean full = true;
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                if (gameBoard[i][j] == EMPTY)
                    full = false;

        return full;
    }

    /** Checks if there is a winner
     *  @return if there is a win on the board
     */
    public boolean isWinner()
    {
        return (verticalWin() || horizontalWin() 
            || diagonalWin(Diagonal.LEFT)
            || diagonalWin(Diagonal.RIGHT));
    }
    
    /** Checks for a vertical win
     *  @return if there is a vertical win
     */
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

    /** Checks for a horizontal win
     *  @return if there is a horizontal win
     */
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

    /** Checks for a diagonal win
     *  @param direction is the direction of the diagonal
     *  @return if there is a win diagonally
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

