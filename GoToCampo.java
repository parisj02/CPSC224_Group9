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
    public GoToCampo()
    {
        super(30, 0, 0);
        NAME = "Go To Campo";
    }

    /**
     * displayPropertyInfo overrides displayPropertyInfo of class Property
     */
    public void displayPropertyInfo()
    {
        System.out.println("The 'Go to Campo' Square");
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
