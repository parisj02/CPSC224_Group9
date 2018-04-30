/**
 * This class creates a Set of two primary properties
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/23/2018
 */

public class Two_Set extends Set
{
    /**
     * constructor for class Two_Set
     * @param P1 is the primary property in the set that comes first on the game board
     * @param P2 is the primary property in the set that comes second on the game board
     */
    public Two_Set(Property P1, Property P2)
    {
        super(2);
        SET[0] = P1;
        SET[1] = P2;

        if(P1.getNumber() == 1)
            NAME = "Purple Set";
        else
            NAME = "Dark Blue Set";
    }
}
