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
    public UtilityProperty(int propertyNum, String name, int XCoord, int YCoord)
    {
        super(propertyNum, 150, 1, XCoord, YCoord);
        this.IS_FOR_SALE = true;
        this.NAME = name;
    }

    /**
     * updateRent overrides updateRent of Property by updating the rent based on the number of Utilities that the
     * player owns
     */
    public void updateRent(ZagopolyDice theDice)
    {
        if(OWNER.getNumberOfUtilities() == 1)
        {
            RENT = 4 * theDice.getTotalRoll();
        }
        if(OWNER.getNumberOfUtilities() == 2)
        {
            RENT = 10 * theDice.getTotalRoll();
        }
    }

    /**
     * isUtility checks if the property is a utility
     * @return true
     */
    public boolean isUtility()
    {
        return true;
    }
}
