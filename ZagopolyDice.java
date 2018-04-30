/**
 * This class creates the game dice for Zagopoly
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 03/28/2018
 */


public class ZagopolyDice
{
    private Die Dice;
    private int[] rolls;
    private int total;

    /**
     * Constructor for class ZagopolyDice
     *
     * @param dNumber is the number of dice (should be 2 in standard Monopoly)
     */
    ZagopolyDice(int dNumber)
    {
        Dice = new Die();
        rolls = new int[dNumber];
        total = 0;
        //numberOfDice = dNumber;
    }

    /**
     * rollDice rolls the Zagopoly game dice
     */
    public void rollDice()
    {
        for (int d = 0; d < rolls.length; d++)
        {
            rolls[d] = Dice.rollDie();
            total += rolls[d];
        }
    }

    /**
     * resetDice resets the total roll (total) back to 0
     */
    public void resetDice()
    {
        for(int r = 0; r < rolls.length; r++)
            rolls[r] = 0;
        total = 0;
    }

    /**
     * displayRoll displays the player's most recent roll
     */
    public void displayRoll()
    {
        if (noRolls())
            System.out.println("The player has not yet rolled anything.");
        else {
            if(doubles())
                System.out.printf("YOU ROLLED A DOUBLES!! ");
            System.out.printf("Player's roll was: ");
            for(int r = 0; r < rolls.length; r++)
            {
                System.out.printf(rolls[r] + ", ");
            }
            System.out.println("so the player will move " + total + " squares unless they are in Campo.");
        }
    }

    /**
     * getTotalRoll returns the sum of all the rolls
     * @return total, the sum of all the rolls
     */
    public int getTotalRoll()
    {
        return total;
    }

    /**
     * doubles checks to see if the player rolled a doubles
     * @return true if both dice have the same value
     */
    public boolean doubles()
    {
        if(rolls[0] == rolls[1] && rolls[0] != 0)
            return true;
        return false;
    }

    /**
     * noRolls checks to see if there are no rolled dice
     *
     * @return true if the count is 0
     */
    public boolean noRolls()
    {
        if (total == 0)
            return true;
        return false;
    }
}
