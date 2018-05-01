/**
 * This class is a child class of class property that creates the "Go to Campo" square
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/15/2018
 */

public class GoToCampo extends Property
{
    /**
     * constructor of class GoToCampo
     */
    public GoToCampo(int XCoord, int YCoord)
    {
        super(30, 0, 0, XCoord, YCoord);
        NAME = "Go To Campo";
    }

    /**
     * isGoToCampoSquare automatically returns true because this is the Go To Campo square
     * @return true
     */
    public boolean isGoToCampoSquare()
    {
        return true;
    }
}
