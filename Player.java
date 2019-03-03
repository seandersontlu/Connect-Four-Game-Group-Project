// Jordyn Martin, Coy Tutt, David Glenewinkel II
// CSCI 434 Project #1, Iteration #0.5
// Player.java
// 2/08/2019
//
// This is the skeletal class method that creates the players and keeps track
// of wins, losses, ties for the game
// connect four.

public class Player
{
    private int numWins;
    private int numLosses;
    private int numTies;
    private int color;
    private String name;
    
    public Player (String name, int color)
    {
        this.name = name;
        this.color = color;
    }

    public String getName()
    {
        return name;
    }

    public int getColor()
    {
        return color;
    }

    public int getNumWins()
    {
        return numWins;
    }

    public int getNumLosses()
    {
        return numLosses;
    }
    
    public int getNumTies()
    {
        return numTies;
    }
    
    public void addWin()
    {
        numWins++;
    }

    public void addLoss()
    {
        numLosses++;
    }

    public void addTie()
    {
        numTies++;
    }

    public void clearScore()
    {
        numWins = 0;
        numLosses = 0;
        numTies = 0;
    }
}
