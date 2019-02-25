// Jordyn M, Coy T, David G
// CSCI 434 Project #1, Iteration #1
// ConnectFourBoardTest.java
// 2-12-19
//
// This is the test class for ConnectFourBoard

import static org.junit.Assert.*;
import org.junit.*;

public class ConnectFourBoardTest
{
    @BeforeClass
    public static void testConnectFour()
    {
        System.out.println("\nThis program performs unit "
            + "testing");
        System.out.println("on the ConnectFourBoard class "
            + "using JUnit.");
    }

    @Test
    public void testVerticalWin()
    {
        String player1 = "Test1";
        String player2 = "Test2";
        ConnectFourBoard board1 = new ConnectFourBoard();

        board1.drop(2, player1);
        board1.drop(3, player2);
        board1.drop(2, player1);
        board1.drop(3, player2);
        board1.drop(2, player1);
        board1.drop(3, player2);
        board1.drop(2, player1);
        
        assertTrue("Player one wins vertically.", board1.verticalWin());
        assertFalse("Player one did not win horizontally.",
            board1.horizontalWin());
        assertFalse("Player one did not win diagonally left.",
            board1.diagonalWin(Diagonal.LEFT));
        assertFalse("Player one did not win diagonally right.",
            board1.diagonalWin(Diagonal.RIGHT));

    }

    @Test
    public void testHorizontalWin()
    {
        String player1 = "Test1";
        String player2 = "Test2";
        ConnectFourBoard board2 = new ConnectFourBoard();

        board2.drop(2, player1);
        board2.drop(2, player2);
        board2.drop(3, player1);
        board2.drop(3, player2);
        board2.drop(4, player1);
        board2.drop(4, player2);
        board2.drop(5, player1);

        assertTrue("Player one wins horizontally.", board2.horizontalWin());
        assertFalse("Player one did not win vertically.",
            board2.verticalWin());
        assertFalse("Player one did not win diagonally left.",
            board2.diagonalWin(Diagonal.LEFT));
        assertFalse("Player one did not win diagonally right.",
            board2.diagonalWin(Diagonal.RIGHT));

    }

    @Test
    public void testDiagonalWinLeft()
    {
        String player1 = "Test1";
        String player2 = "Test2";
        ConnectFourBoard board3 = new ConnectFourBoard();

        board3.drop(4, player1);
        board3.drop(3, player2);
        board3.drop(3, player1);
        board3.drop(2, player2);
        board3.drop(4, player1);
        board3.drop(2, player2);
        board3.drop(2, player1);
        board3.drop(1, player2);
        board3.drop(1, player1);
        board3.drop(1, player2);
        board3.drop(1, player1);

        assertTrue("Player one wins diagonally (LEFT).",
            board3.diagonalWin(Diagonal.LEFT));
    }

    @Test
    public void testDiagonalWinRight()
    {
        String player1 = "Test1";
        String player2 = "Test2";
        ConnectFourBoard board4 = new ConnectFourBoard();
        
        board4.drop(2, player1);
        board4.drop(3, player2);
        board4.drop(3, player1);
        board4.drop(4, player2);
        board4.drop(2, player1);
        board4.drop(4, player2);
        board4.drop(4, player1);
        board4.drop(5, player2);
        board4.drop(5, player1);
        board4.drop(5, player2);
        board4.drop(5, player1);

        assertTrue("Player one wins diagonally (RIGHT).",
            board4.diagonalWin(Diagonal.RIGHT));
    }

    @AfterClass
    public static void testEnd()
    {
        System.out.println("\nEnd of testing for "
            + "the ConnectFourBoard class.");
    }
}
