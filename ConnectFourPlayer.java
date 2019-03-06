// Jordyn Martin, Coy Tutt, David Glenewinkel II
// CSCI 434 Project #1, Iteration #0.5
// ConnectFourPlayer.java
// 2/08/2019
//
// This is the skeletal class method that creates the players and keeps track
// of wins, losses, ties for the game
// connect four.

/**
 * This  class represents the player
 * @author  Sarah, Adrian, Scott, and Montrel
 */

public class ConnectFourPlayer
{
    private int numWins;
    private int numLosses;
    private int numTies;
    private int color;
    private String name;
    
    /**
     * @param name  players name
     * @param color integer value for the asignned chips color
     * Creates the player
     */
    public ConnectFourPlayer (String name, int color)
    {
        this.name = name;
        this.color = color;
    }

    /** Returns the player's name
     */
    public String getName()
    {
        return name;
    }

    /** Returns the player's assigned color
     */
    public int getColor()
    {
        return color;
    }

    /** Returns the number of wins
     */
    public int getNumWins()
    {
        return numWins;
    }

    /** Returns the number of losses
     */
    public int getNumLosses()
    {
        return numLosses;
    }
    
    /** Returns the number of ties
     */
    public int getNumTies()
    {
        return numTies;
    }
    
    /** Increments the number of wins
     */
    public void addWin()
    {
        numWins++;
    }

    /** Increments the number of losses
     */
    public void addLoss()
    {
        numLosses++;
    }

    /** Increments the number of ties
     */
    public void addTie()
    {
        numTies++;
    }

    /** Resets the number of wins, losses, and ties to 0
     */
    public void clearScore()
    {
        numWins = 0;
        numLosses = 0;
        numTies = 0;
    }
}
