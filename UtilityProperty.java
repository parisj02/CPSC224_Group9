/**
 * This class is a child class of class Property that creates a utility square
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/15/2018
 */

public class UtilityProperty extends Property
{
    /**
     * constructor for class UtilityProperty
     * @param propertyNum is the property's position on the board
     * @param name is the name of the property
     */
    public UtilityProperty(int propertyNum, String name)
    {
        super(propertyNum, 150, 1);
        this.IS_FOR_SALE = true;
        this.NAME = name;
    }

    /**
     * displayPropertyInfo of class UtilityProperty overrides displayPropertyInfo of class Property
     */
    public void displayPropertyInfo()
    {
        System.out.println("Property Name: " + this.NAME);
        System.out.println("Property Price: " + this.PRICE);
        System.out.println("Property Rent: 4 times the player's roll");
        System.out.println("Property Mortgage: " + this.MORTGAGE);
        if(isOwned())
            System.out.println("Property Owner: " + this.OWNER.getName());
        else
            System.out.println("Property is unowned.");
    }
}
