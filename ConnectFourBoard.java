// Jordyn Martin, Coy Tutt, David Glenewinkel
// CSCI 434 Project #1, Iteration #1 
// ConnectFourBoard.java
// 2/08/2019
//
// This is the class method that creates the board for the game
// connect four.

import java.util.Scanner;
import java.util.Arrays;

enum Diagonal 
{
    LEFT, RIGHT;
}

public class ConnectFourBoard
{
    
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    private char[][] gameBoard = new char[ROWS][COLUMNS];
    String currentPlayer;
    String player1;
    String player2;
    int totalMovesPlayed = 0;
    public static final int MAX_TURNS = 42;
    int score1 = 0;
    int score2 = 0;
    char answer;

    Scanner scan = new Scanner(System.in);

    public ConnectFourBoard()
    {
        // Will create board to play connect four with.
        fillBoard(' ');
        totalMovesPlayed = 0;
    }

    public void enterPlayer()
    {

        System.out.print("Enter player one's name: ");
        player1 = scan.next();
        System.out.print("Enter player two's name: ");
        player2 = scan.next();

        currentPlayer = player1;
    }

    public void playGame()
    {
       while (true)
       {
            showBoard();

            System.out.println(currentPlayer + ", please enter the column");
            System.out.print("you would like to drop your token: ");
            int col = scan.nextInt();
            totalMovesPlayed++;

            if (drop(col, currentPlayer))
            {
                if (checkWinner())
                {
                    System.out.println("\n\t--" + currentPlayer + " wins!--");
                    showBoard();
                    fillBoard(' ');
                    System.out.println();
                    if (currentPlayer.equals(player1))
                        score1++;
                    else
                        score2++;
                    System.out.println(player1 + "'s score: " + score1);
                    System.out.println(player2 + "'s score: " + score2);
                    return;
                }
                    currentPlayer = switchPlayer(currentPlayer);
            }
        }
    }

    public void runGame()
    {
        do
        {
            playGame();
            System.out.print("\nWould you like to play again? (Y/N) ");
            answer = scan.next().charAt(0);
        }
       while (answer == 'Y' || answer == 'y');
    }

    public void fillBoard(char ch)
    {
        for (int row = 0; row < ROWS; row++)
        {
            Arrays.fill(gameBoard[row], ch);
        }
    }

    public void showBoard()
    {
        System.out.println();
        for (int header = 0; header < COLUMNS; header++)
        {
            System.out.print("| " + header + " ");
        }

        System.out.print("|");

        System.out.println();
        System.out.println("-----------------------------");
        for (int row = 0; row < ROWS; row++)
        {
            System.out.print("|");
            for (int col = 0; col < COLUMNS; col++)
            {
                System.out.print(" " + gameBoard[row][col] + " |");
            }
            System.out.println();
        }
    }

    public boolean checkWinner()
    {
        return (verticalWin() || horizontalWin() 
            || diagonalWin(Diagonal.LEFT)
            || diagonalWin(Diagonal.RIGHT));
    }

    // Checking vertical
    public boolean verticalWin()
    {
        boolean result = false;
    
        for (int col = 0; col < COLUMNS; col++)
        {
            // subtracting 3 so the for loop doesn't need to go through more
            // than it needs to.
            for (int row = 0; row < ROWS - 3; row++)
            {
                if (gameBoard[row][col] != ' ' && gameBoard[row][col] ==
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
    public boolean horizontalWin()
    {
        boolean result = false;

        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS - 3; col++)
            {
                if (gameBoard[row][col] != ' ' && gameBoard[row][col] ==
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

    public boolean diagonalWin(Diagonal direction)
    {
        boolean result = false;

        // Checking left diagonal
        if (direction == Diagonal.LEFT)
        {
            for (int row = 0; row < ROWS - 3; row++)
            {
                for (int col = 0; col < COLUMNS - 3; col++)
                {
                    if (gameBoard[row][col] != ' ' && gameBoard[row][col] ==
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
                    if (gameBoard[row][col] != ' ' && gameBoard[row][col] ==
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

    public boolean drop(int col, String currentPlayer)
    {
        // Checking for valid column
        if (col < 0 || col >= COLUMNS)
        {
            System.out.println("invalid column number provided");
            return false;
        }

        if (totalMovesPlayed == MAX_TURNS)
        {
            System.out.println("Board is full, Players tie.");
        }

        char color = 'Y';
        if (currentPlayer == player2)
        {
            color = 'R';
        }

        for (int row = ROWS - 1; row >= 0; row--)
        {
            if (gameBoard[row][col] == ' ')
            {
                gameBoard[row][col] = color;
                return true;
            }
        }
        return false;
    }

    public String switchPlayer(String currentPlayer)
    {
        if (currentPlayer == player1)
        {
            return player2;
        }
        else
        {
            return player1;
        }
    }
}

