/**
 * This class is a child class of class property that creates a Tax Square
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/15/2018
 */


public class TaxSquare extends Property
{
    /**
     * constructor for class TaxSquare
     * @param squareNum is the tax square's position on the board
     * @param tax is the amount the player will have to pay when they land on the tax square
     * @param name is the name of the tax square
     */
    public TaxSquare(int squareNum, int tax, String name, int XCoord, int YCoord)
    {
        super(squareNum, 0, tax, XCoord, YCoord);
        this.NAME = name;
    }

    /**
     * isTaxSquare checks if the current square is a tax square
     * @return true
     */
    public boolean isTaxSquare()
    {
        return true;
    }
}
