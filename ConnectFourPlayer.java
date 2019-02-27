// Jordyn Martin, Coy Tutt, David Glenewinkel II
// CSCI 434 Project #1, Iteration #0.5
// ConnectFourPlayer.java
// 2/08/2019
//
// This is the skeletal class method that creates the players and keeps track
// of wins, losses, ties for the game
// connect four.

public class ConnectFourPlayer
{
    private int numWins;
    private int numLosses;
    private String name;
    
    public ConnectFourPlayer(String name)
    {
        this.name = name;
        numWins = 0;
        numLosses = 0;
    }

    public int getNumWins()
    {
        return numWins;
    }

    public int getNumLosses()
    {
        return numLosses;
    }

    public String getName()
    {
        return name;
    }

    public void addWin()
    {
        numWins++;
    }

    public void addLoss()
    {
        numLosses++;
    }

    public void clearScore()
    {
        numWins = 0;
        numLosses = 0;
    }

    public String toString()
    {
        return (name + ": " + numWins + "/" + numLosses);
    }
}
