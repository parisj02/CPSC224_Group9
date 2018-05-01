/**
 * This class is a child class of class property that creates the campo square
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/15/2018
 */

public class CampoSquare extends Property
{
    /**
     * constructor for class CampoSquare
     */
    public CampoSquare(int XCoord, int YCoord)
    {
        super(10, 0, 0, XCoord, YCoord);
        NAME = "Campo Square";
    }

    /**
     * isCampoSquare checks to see if the current square is the campo square
     * @return true
     */
    public boolean isCampoSquare()
    {
        return true;
    }
}
