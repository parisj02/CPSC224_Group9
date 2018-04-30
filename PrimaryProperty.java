/**
 * This class extends class Property in order to create a primary, or "color" property
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/11/2018
 */

public class PrimaryProperty extends Property
{
    private int HOUSE_PRICE;
    private int NUM_HOUSES;
    private boolean HOTEL;

    /**
     * constructor for class PrimaryProperty
     *
     * @param propertyNum   is primary property's position on the board
     * @param propertyPrice is the price of purchasing the primary property
     * @param propertyRent  is the rent that must be paid on that primary property to the property's owner
     *                      by other players who land there
     * @param name          is the name of the primary property (e.g. Kennedy Apartments)
     */
    public PrimaryProperty(int propertyNum, int propertyPrice, int propertyRent, int priceOfAHouse, String name, int XCoord, int YCoord)
    {
        super(propertyNum, propertyPrice, propertyRent, XCoord, YCoord);
        this.HOUSE_PRICE = priceOfAHouse;
        this.NUM_HOUSES = 0;
        this.HOTEL = false;
        this.IS_FOR_SALE = true;
        this.NAME = name;
    }

    /**
     * getSetNumber overrides getSetNumber of Property and finds out which set this property is in
     * @return setNumber
     */
    public int getSetNumber(The_Sets Sets) throws SetNotFoundException
    {
        int setNumber = -1;
        for(int p = 0; p < Sets.getNumberOfSets(); p++)
        {
            if(Sets.getSet(p).containsProperty(this))
                setNumber = p;
        }
        if(setNumber != -1)
        {
            return setNumber;
        }else{
            throw new SetNotFoundException("This set does not exist.");
        }
    }

    /**
     * updateRent overrides updateRent of property by updating it depending on the properties of the set it is in
     * and on the number of houses and hotels that the property has on it
     * @param theDice is the game dice
     */
    public void updateRent(ZagopolyDice theDice)
    {
        int newRent = 0;
        if(NUM_HOUSES == 0)
            newRent = RENT * 2;
        else if(NUM_HOUSES == 1) // if 1 house, multiply most rents by five (some are different)
        {
            newRent = RENT / 2;
            RENT = newRent;
            newRent = RENT * 5;
            if(PROPERTY_NUMBER == 34)
                newRent += 10;
            if(PROPERTY_NUMBER == 39)
                newRent = RENT * 4;
        }
        else if(NUM_HOUSES == 2) // if 2 houses, triple most rents (some are different)
        {
            newRent = RENT * 3;
            if(PROPERTY_NUMBER == 9 || PROPERTY_NUMBER == 19 || PROPERTY_NUMBER == 21 || PROPERTY_NUMBER == 23)
                newRent -= 20;
            if(PROPERTY_NUMBER == 16 || PROPERTY_NUMBER == 18)
                newRent -= 10;
            if(PROPERTY_NUMBER == 37)
                newRent -= 25;
        }
        else if(NUM_HOUSES == 3) // if 3 houses, triple most rents again (some are different)
        {
            newRent = RENT * 3;
            if(PROPERTY_NUMBER == 14)
                newRent -= 40;
            if(PROPERTY_NUMBER == 16 || PROPERTY_NUMBER == 18 || PROPERTY_NUMBER == 21 || PROPERTY_NUMBER == 23)
                newRent -= 50;
            if(PROPERTY_NUMBER == 19)
                newRent -= 60;
            if(PROPERTY_NUMBER == 24)
                newRent -= 150;
            if(PROPERTY_NUMBER == 26 || PROPERTY_NUMBER == 27)
                newRent -= 190;
            if(PROPERTY_NUMBER == 29)
                newRent -= 230;
            if(PROPERTY_NUMBER == 31 || PROPERTY_NUMBER == 32)
                newRent -= 270;
            if(PROPERTY_NUMBER == 34)
                newRent-= 350;
            if(PROPERTY_NUMBER == 37 || PROPERTY_NUMBER == 39)
                newRent -= 400;
        }
        else if(NUM_HOUSES == 4) // if 4 houses, increases in rent vary significantly, but most common increase is $200
        {
            newRent = RENT + 200;
            if(PROPERTY_NUMBER == 1)
                newRent -= 130;
            if(PROPERTY_NUMBER == 3)
                newRent -= 60;
            if(PROPERTY_NUMBER == 6 || PROPERTY_NUMBER == 8)
                newRent -= 70;
            if(PROPERTY_NUMBER == 9)
                newRent -= 50;
            if(PROPERTY_NUMBER == 11 || PROPERTY_NUMBER == 13 || PROPERTY_NUMBER == 21 || PROPERTY_NUMBER == 23
                    || PROPERTY_NUMBER == 24 || PROPERTY_NUMBER == 26 || PROPERTY_NUMBER == 27
                    || PROPERTY_NUMBER == 29)
                newRent -= 25;
            if(PROPERTY_NUMBER == 39)
                newRent += 100;
        }
        if(HOTEL) // if a hotel, the maximum rent is reached
        {
            newRent = RENT + 175;
            if(PROPERTY_NUMBER == 1)
                newRent -= 85;
            if(PROPERTY_NUMBER == 3)
                newRent -= 45;
            if(PROPERTY_NUMBER == 6 || PROPERTY_NUMBER == 8 || PROPERTY_NUMBER == 9)
                newRent -= 25;
            if(PROPERTY_NUMBER == 11 || PROPERTY_NUMBER == 13)
                newRent -= 50;
            if(PROPERTY_NUMBER == 14 || PROPERTY_NUMBER == 16 || PROPERTY_NUMBER == 18 || PROPERTY_NUMBER == 19
                    || PROPERTY_NUMBER == 34 || PROPERTY_NUMBER == 37)
                newRent += 25;
            if(PROPERTY_NUMBER == 39)
                newRent += 125;
        }

        RENT = newRent;
    }

    /**
     * addHouse overrides addHouse of Property and adds a house to the property
     */
    public void addHouse()
    {
        if(NUM_HOUSES < 4)
        {
            NUM_HOUSES++;
            System.out.println("House added!");
        }
        else{
            System.out.println("Cannot add house because this property already has the max number of houses");
        }
    }

    /**
     * addHotel overrides addHotel of Property and adds a hotel to the current Property
     */
    public void addHotel()
    {
        if(!HOTEL)
        {
            HOTEL = true;
            System.out.println("Your property now has a hotel!!!!");
        }
        else
            System.out.println("Cannot add hotel to this property because there is already a hotel here.");
    }

    /**
     * removeHouse overrides removeHouse of Property by removing a house from the property
     */
    public void removeHouse()
    {
        if(NUM_HOUSES > 0)
            NUM_HOUSES--;
        else
            System.out.println("This property does not have any houses to remove.");
    }

    /**
     * removeHotel overrides removeHotel of Property by removing a hotel from the property
     */
    public void removeHotel()
    {
        if(HOTEL)
            HOTEL = false;
        else
            System.out.println("This property does not have a hotel to remove.");
    }

    /**
     * displayPropertyInfo overrides displayPropertyInfo of Property by displaying the number of houses and hotels
     */
    public void displayPropertyInfo()
    {
        System.out.println("Property Name: " + this.NAME);
        System.out.println("Property Price: " + this.PRICE);
        System.out.println("Property Rent: " + this.RENT);
        //System.out.println("Property Mortgage: " + this.MORTGAGE);
        if(isOwned())
            System.out.println("Property Owner: Player " + this.OWNER.getPlayerNum());
        else
            System.out.println("Property is unowned.");
        if(this.NUM_HOUSES == 0)
            System.out.println("This property currently has no houses or hotels.");
        if(this.NUM_HOUSES > 0 && !this.HOTEL)
            System.out.println("This property has " + this.NUM_HOUSES + " houses.");
        if(this.HOTEL)
            System.out.println("This property has a hotel. It's rent is now maxed!!");
    }

    /**
     * getNumHouses returns the number of houses on this property
     * @return NUM_HOUSES
     */
    public int getNumHouses()
    {
        return NUM_HOUSES;
    }

    /**
     * getHousePrice overrides getHousePrice of Property and gets the price of adding a house to this property
     * @return HOUSE_PRICE
     */
    public int getHousePrice()
    {
        return HOUSE_PRICE;
    }

    /**
     * hasHotel checks to see if this property has a hotel
     * @return HOTEL
     */
    public boolean hasHotel()
    {
        return HOTEL;
    }
}
