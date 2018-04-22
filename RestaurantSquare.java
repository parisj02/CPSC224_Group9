/**
 * This class is a child class of class property that creates a Restaurant Square
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/15/2018
 */

public class RestaurantSquare extends Property
{
    /**
     * constructor for class RestaurantSquare
     * @param propertyNumber is the position of the restaurant square on the board
     * @param name is the name of the property
     */
    public RestaurantSquare(int propertyNumber, String name)
    {
        super(propertyNumber, 200, 50);
        this.IS_FOR_SALE = true;
        this.NAME = name;
    }
}
