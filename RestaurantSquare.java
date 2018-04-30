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
    public RestaurantSquare(int propertyNumber, String name, int XCoord, int YCoord)
    {
        super(propertyNumber, 200, 50, XCoord, YCoord);
        this.IS_FOR_SALE = true;
        this.NAME = name;
    }

    /**
     * updateRent overrides updateRent of Property and changes the property's rent
     * @param theDice is the game dice
     */
    public void updateRent(ZagopolyDice theDice)
    {
        if(OWNER.getNumberOfRestaurants() == 2)
        {
            RENT = 100;
        }
        else if(OWNER.getNumberOfRestaurants() == 3)
        {
            RENT = 150;
        }
        else if(OWNER.getNumberOfRestaurants() == 4)
        {
            RENT = 200;
        } else{
            RENT = 50;
        }
    }

    /**
     * isRestaurant checks to see if the current square is a restaurant square
     * @return true
     */
    public boolean isRestaurant()
    {
        return true;
    }
}
