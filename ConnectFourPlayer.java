// Sarah, Adrian, Montrell, Scott
// CSCI 434 Project #1, Iteration #3
// ConnectFourPlayer.java
// 3/06/2019
//
// This class creates the players and keeps track
// of wins, losses, ties for the game connect four.

/**
 * This  class represents the player
 * @author Jordyn Martin, Coy Tutt, David Glenewinkel II
 * @author  Sarah, Adrian, Scott, and Montrel
 */

public class ConnectFourPlayer implements ConnectFourConstants
{
    private String name;
    private int numWins;
    private int numLosses;
    
    /**
     * @param name  players name
     * Creates the player
     */
    public ConnectFourPlayer (String name)
    {
        this.name = name;
        numWins = 0;
        numLosses = 0;
    }

    /** Returns the player's name
     */
    public String getName()
    {
        return name;
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

    /** Resets the players' stats 
     */
    public void clearScore()
    {
        numWins = 0;
        numLosses = 0;
    }
}
