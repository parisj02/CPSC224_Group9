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
    public CampoSquare()
    {
        super(10, 0, 0);
        NAME = "Campo Square";
    }

    /**
     * displayPropertyInfo for class CampoSquare overrides displayPropertyInfo of class Player
     */
    public void displayPropertyInfo()
    {
        System.out.println("The Campo Square");
    }
}
