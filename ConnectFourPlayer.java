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
    private int numTies;
    private String name;
    
    public ConnectFourPlayer(String name, String color)
    {
        this.name = name;
    }

    public int getNumWins()
    {
        return numWins;
    }

    public void addWin()
    {
        numWins++;
    }

    public int getNumLosses()
    {
        return numLosses;
    }

    public void addLoss()
    {
        numLosses++;
    }

    public int getNumTies()
    {
        return numTies;
    }

    public void addTie()
    {
        numTies++;
    }

    public String getName()
    {
        return name;
    }

    public void clearScore()
    {
        numWins = 0;
        numLosses = 0;
        numTies = 0;
    }
}
