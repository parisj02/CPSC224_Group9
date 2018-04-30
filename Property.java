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
    //protected int MORTGAGE; could be a future implementation
    protected int XPosition;
    protected int YPosition;
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
    public Property(int propertyNum, int propertyPrice, int propertyRent, int XCoord, int YCoord)
    {
        this.PROPERTY_NUMBER = propertyNum;
        this.PRICE = propertyPrice;
        this.RENT = propertyRent;
        //this.MORTGAGE = (propertyPrice/2);
        this.XPosition = XCoord;
        this.YPosition = YCoord;
        this.IS_MORTGAGED = false;
        this.IS_FOR_SALE = false;
        this.NAME = "Free Parking";
    }

    /**
     * updateRent changes the property's rent
     * @param theDice is the game dice
     */
    public void updateRent(ZagopolyDice theDice)
    {
        System.out.println("Can't update the rent of Free Parking because there is no rent here.");
    }

    /**
     * addOwner makes a player the owner of this property
     * @param newOwner is the new owner of the property
     */
    public void addOwner(Player newOwner)
    {
        this.OWNER = newOwner;
    }

    /**
     * removeOwner takes the property away from the owner's ownership
     */
    public void removeOwner()
    {
        this.OWNER = null;
    }

    /**
     * addHouse prints the message that tells the player they cannot put a house on this property
     */
    public void addHouse()
    {
        System.out.println("You cannot add a house to a property that does not belong to a set.");
    }

    /**
     * addHotel prints a message that tells the player they cannot put a hotel on this property
     */
    public void addHotel()
    {
        System.out.println("You cannot add a hotel to a property that does not belong to a set.");
    }

    /**
     * removeHouse prints a message that tells the player they can't remove a house from this property
     */
    public void removeHouse()
    {
        System.out.println("You cannot remove a house from a property that doesn't belong to a set.");
    }

    /**
     * removeHotel prints a message that tells the player they can't remove a hotel from this property
     */
    public void removeHotel()
    {
        System.out.println("You cannot remove a hotel from a property that doesn't belong to a set.");
    }
    /**
     * mortgageProperty mortgages the property for the owner
     */
    /*public void mortgageProperty()
    {
        this.IS_MORTGAGED = true;
    }*/

    /**
     * payOwnerRent pays the owner of the property the amount that the rent is worth
     */
    public void payOwnerRent()
    {
        OWNER.payPlayer(RENT);
    }

    /**
     * unMortgageProperty reactivates the property for the owner
     */
    /*public void unMortgageProperty()
    {
        this.IS_MORTGAGED = false;
    }*/

    /**
     * getServiceCard gives the player a service card
     * @param currentPlayer is the player who landed on the square
     */
    public void getServiceCard(Player currentPlayer, Board gameBoard, GameBoardUI gui, int currentPiece)
    {
        System.out.println("Player will not receive a service card because this is not a service square.");
    }

    /**
     * getChanceCard gives the player a chance card
     * @param currentPlayer is the player who landed on the current square
     */
    public void getChanceCard(Player currentPlayer, Board gameBoard, GameBoardUI gui, int currentPiece)
    {
        System.out.println("Player will not receive a chance card because this is not a chance square.");
    }

    /**
     * displayPropertyInfo displays all of the property's key information
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
     * getHousePrice returns no value
     * @return 0
     */
    public int getHousePrice()
    {
        return 0;
    }

    /**
     * getMortgage returns the mortgage value of the property
     * @return MORTGAGE
     */
    /*public int getMortgage()
    {
        return this.MORTGAGE;
    }*/

    /**
     * getSetNumber finds out which set this property is in
     * @return
     */
    public int getSetNumber(The_Sets Sets) throws SetNotFoundException
    {
        throw new SetNotFoundException("This is not the right type of property to be in a set.");
    }

    /**
     * getX returns the property's x coordinate on the GUI
     * @return XPosition
     */
    public int getX()
    {
        return XPosition;
    }

    /**
     * getY returns the property's y coordinate on the GUI
     * @return YPosition
     */
    public int getY()
    {
        return YPosition;
    }

    /**
     * getNumHouses doesn't return anything because this property can't have houses
     * @return 0
     */
    public int getNumHouses()
    {
        return 0;
    }

    /**
     * getOwnerNum gets the player number of the owner
     * @return OWNER.getPlayerNum
     */
    public int getOwnerNum()
    {
        return OWNER.getPlayerNum();
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
    /*public boolean isMortgaged()
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
    }*/

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
     * isCampoSquare checks to see if the current square is the campo square
     * @return false
     */
    public boolean isCampoSquare()
    {
        return false;
    }

    /**
     * isUtility checks if the current property is a utility
     * @return false
     */
    public boolean isUtility()
    {
        return false;
    }

    /**
     * isRestaurant checks to see if the current property is a restaurant
     * @return false
     */
    public boolean isRestaurant()
    {
        return false;
    }

    /**
     * hasHotel checks to see if the current property has a hotel
     * @return false
     */
    public boolean hasHotel()
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

    /**
     * getOwnerName calls OWNER.getName() to get the owner's name
     * @return OWNER.getName()
     */
    /*public String getOwnerName()
    {
        return OWNER.getName();
    }*/

    /**
     * getOwner returns the owner of this property
     * @return OWNER
     */
    public Player getOwner()
    {
        return OWNER;
    }
}
