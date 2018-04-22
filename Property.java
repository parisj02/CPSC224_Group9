/**
 * This class creates a property square
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/11/2018
 */

public class Property
{
    protected int PROPERTY_NUMBER;
    protected int PRICE;
    protected int RENT;
    protected int MORTGAGE;
    protected boolean IS_MORTGAGED;
    protected boolean IS_FOR_SALE;
    protected String NAME;
    protected Player OWNER;

    /**
     * constructor for class Property
     * @param propertyNum is the property's position on the board
     * @param propertyPrice is the price of purchasing the property
     * @param propertyRent is the rent that must be paid on that property to the property's owner
     *                     by other players who land there
     */
    public Property(int propertyNum, int propertyPrice, int propertyRent)
    {
        this.PROPERTY_NUMBER = propertyNum;
        this.PRICE = propertyPrice;
        this.RENT = propertyRent;
        this.MORTGAGE = (propertyPrice/2);
        this.IS_MORTGAGED = false;
        this.IS_FOR_SALE = false;
        this.NAME = "Free Parking";
    }

    /**
     * updateRent changes the property's rent
     * @param updatedRent is the new rent of the property
     */
    public void updateRent(int updatedRent)
    {
        this.RENT = updatedRent;
    }

    /**
     * addOwner makes a player the owner of this property
     * @param newOwner is the new owner of the property
     */
    public void addOwner(Player newOwner) throws CloneNotSupportedException
    {
        this.OWNER = newOwner.clone();
    }

    /**
     * removeOwner takes the property away from the owner's ownership
     */
    public void removeOwner()
    {
        this.OWNER = null;
    }

    /**
     * mortgageProperty mortgages the property for the owner
     */
    public void mortgageProperty()
    {
        this.IS_MORTGAGED = true;
    }

    /**
     * unMortgageProperty reactivates the property for the owner
     */
    public void unMortgageProperty()
    {
        this.IS_MORTGAGED = false;
    }

    /**
     *
     */
    public void getServiceCard(Player currentPlayer)
    {
        System.out.println("Unimplemented Function.");
    }

    /**
     * displayPropertyInfo displays all of the property's key information
     */
    public void displayPropertyInfo()
    {
        System.out.println("Property Name: " + this.NAME);
        System.out.println("Property Price: " + this.PRICE);
        System.out.println("Property Rent: " + this.RENT);
        System.out.println("Property Mortgage: " + this.MORTGAGE);
        if(isOwned())
            System.out.println("Property Owner: " + this.OWNER.getName());
        else
            System.out.println("Property is unowned.");
    }

    /**
     * getNumber returns the position of the property on the board
     * @return PROPERTY_NUMBER
     */
    public int getNumber()
    {
        return this.PROPERTY_NUMBER;
    }

    /**
     * getPrice() returns the price for buying the property
     * @return PRICE
     */
    public int getPrice()
    {
        return this.PRICE;
    }

    /**
     * getRent returns the property's current rent
     * @return RENT
     */
    public int getRent()
    {
        return this.RENT;
    }

    /**
     * getMortgage returns the mortgage value of the property
     * @return MORTGAGE
     */
    public int getMortgage()
    {
        return this.MORTGAGE;
    }

    /**
     * isOwned checks if this property is owned
     * @return true if OWNER is not null
     */
    public boolean isOwned()
    {
        if(this.OWNER == null)
            return false;
        return true;
    }

    /**
     * isMortgaged checks to see if the property is mortgaged
     * @return true if it IS_MORTGAGED is true
     */
    public boolean isMortgaged()
    {
        if(!isOwned())
        {
            return false;
        }
        if(!this.IS_MORTGAGED)
        {
            return false;
        }
        return true;
    }

    /**
     * isForSale determines if the property can be bought
     * @return true if the property can be bought
     */
    public boolean isForSale()
    {
        if(IS_FOR_SALE)
            return true;
        return false;
    }

    /**
     * isTaxSquare determines if the current property is one of two tax squares
     * @return
     */
    public boolean isTaxSquare()
    {
        return false;
    }

    /**
     * isCardSquare determines if the current square is a card sqaure
     * @return false
     */
    public boolean isCardSquare()
    {
        return false;
    }

    /**
     * isGoToCampoSquare checks to see if the current property is the Go To Campo square
     * @return false
     */
    public boolean isGoToCampoSquare()
    {
        return false;
    }

    /**
     * getName gets the name of the property
     * @return NAME, the property name
     */
    public String getName()
    {
        return NAME;
    }
}
