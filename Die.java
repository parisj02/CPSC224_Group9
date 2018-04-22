/**
 * This class creates a virtual die
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 3.0, 03/28/2018
 */

public class Die
{
    /**
     * rollDie simulates the rolling of a single die
     * @return the value of the resulting simulated roll
     */
    public int rollDie()
    {
        int roll = (int)(Math.random() * 6 + 1);
        return roll;
    }
}
