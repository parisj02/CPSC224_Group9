/**
 * This class stores information about a player in Zag-opoly
 * including the player's account, the player's properties, the player's location,
 * and the player's status (in the game or eliminated)
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 03/28/2018
 */

import java.util.ArrayList;

public class Player implements Cloneable
{
    private int PLAYER_NUM;
    private int PLAYER_PRIORITY;
    private int POSITION;
    private int ACCOUNT;
    private int RESTAURANTS_OWNED;
    private int UTILITIES_OWNED;
    private int HOUSES_OWNED;
    private int HOTELS_OWNED;
    private int TURNS_IN_JAIL;
    private boolean PASSED_GO;
    private boolean IS_FREE;
    private boolean GET_OUT_OF_CAMPO_CARD;
    //private String NAME;
    private ArrayList<Property> HAND;
    private ArrayList<Set> OWNED_SETS;

    /**
     * Constructor for class Player
     * @param pNumber is the number of players
     */
    public Player(int pNumber)
    {
        PLAYER_NUM = pNumber;
        //NAME = pName;
        POSITION = 0;
        ACCOUNT = 1500;
        RESTAURANTS_OWNED = 0;
        UTILITIES_OWNED = 0;
        HOUSES_OWNED = 0;
        HOTELS_OWNED = 0;
        TURNS_IN_JAIL = 0;
        PASSED_GO = false;
        IS_FREE = true;
        GET_OUT_OF_CAMPO_CARD = false;
        HAND = new ArrayList<>();
        OWNED_SETS = new ArrayList<>();
    }

    /**
     * setPriority sets a player's priority, which determines when their turn will be
     * @param p is the new priority
     */
    public void setPriority(int p)
    {
        PLAYER_PRIORITY = p;
    }

    /**
     * payPlayer adds money to player's account
     * @param payment is the amount of money to be added to the account
     */
    public void payPlayer(int payment)
    {
        ACCOUNT += payment;
    }

    /**
     * finePlayer removes money from a player's account
     * @param fine is the amount of money to be removed from a player's account
     */
    public void finePlayer(int fine)
    {
        ACCOUNT -= fine;
    }

    /**
     * addRestaurant increments the number of restaurants that the player owns
     */
    public void addRestaurant()
    {
        RESTAURANTS_OWNED++;
    }

    /**
     * addUtility increments the number of Utilities that the player owns
     */
    public void addUtility()
    {
        UTILITIES_OWNED++;
    }

    /**
     * buyHouse increments the number of houses that the player owns by 1
     */
    public void buyHouse()
    {
        HOUSES_OWNED++;
    }

    /**
     * buyHotel increments the number of hotels that the player owns by 1
     */
    public void buyHotel()
    {
        HOTELS_OWNED++;
    }

    /**
     * mortgageProperty mortgages one of the player's properties to give them a loan to pay off debts if needed
     */
    /*public void mortgageProperty(int propertyNum)
    {
        Property[] ownedProperties = new Property[HAND.size()];
        ownedProperties = HAND.toArray(ownedProperties);

        if(!ownedProperties[propertyNum].isMortgaged())
        {
            if(ownedProperties[propertyNum].hasHotel()) // if the current property has a hotel then sell that hotel
            {
                ownedProperties[propertyNum].removeHotel();
                payPlayer(ownedProperties[propertyNum].getHousePrice()/2);
            }
            else if(ownedProperties[propertyNum].getNumHouses() > 0) // if the current property has 1 or more houses
            {
                ownedProperties[propertyNum].removeHouse();
                payPlayer(ownedProperties[propertyNum].getHousePrice()/2);
            } else{ // if no houses or hotels or isn't in a set then mortgage
                ownedProperties[propertyNum].mortgageProperty();
                payPlayer(ownedProperties[propertyNum].getMortgage());
            }
        }
    }*/

    /**
     * updateRestaurantRent updates the rents of all the restaurants that the player owns
     * @param theDice is the game dice
     */
    public void updateRestaurantRents(ZagopolyDice theDice)
    {
        Property[] ownedProperties = new Property[HAND.size()];
        ownedProperties = HAND.toArray(ownedProperties);
        for(int p = 0; p < HAND.size(); p++)
        {
            if(ownedProperties[p].isRestaurant())
            {
                ownedProperties[p].updateRent(theDice);
            }
        }
    }

    /**
     * movePlayer moves the player a given number of squares on the board and updates PASSED_GO if
     * the player passes "Go" on the current roll
     * @param movement is the number of squares a player is to be moved
     */
    public void movePlayer(int movement)
    {
        POSITION += movement;
        PASSED_GO = false;
        if(POSITION > 39) // passed go
        {
            POSITION -= 40;
            PASSED_GO = true;
        }
    }

    /**
     * relocatePlayer moves player anywhere on the board depending on relocation value
     * @param relocation is the square on the board the player will be moved to
     */
    public void relocatePlayer(int relocation)
    {
        PASSED_GO = false;
        if(relocation <= POSITION)
            PASSED_GO = true;
        POSITION = relocation;
    }

    /**
     * takeOwnershipOfProperty adds a property to the player's hand
     * @param P is the property to be added
     */
    public void takeOwnershipOfProperty(Property P)
    {
        HAND.add(P);
    }

    /**
     * takeOwnerShipOfSets adds a set to the player's OWNED_SETS if the player owns all of the properties in that set
     * @param S is the new set
     */
    public void takeOwnerShipOfSet(Set S)
    {
        if(S.isOwned())
        {
            OWNED_SETS.add(S);
            System.out.println("You now own the " + S.getSetName() + " !");
        }
        else
            System.out.println("Cannot add set to player hand yet.");
    }

    /**
     * removeProperty takes the property away from the player
     * @param P is the property to be removed from the player's hand
     */
    public void removeProperty(Property P)
    {
        HAND.remove(P);
    }

    /**
     * imprisonPlayer puts the player in jail
     */
    public void imprisonPlayer()
    {
        POSITION = 10;
        IS_FREE = false;
    }

    /**
     * incrementTurnsInJail increases the amount of turns the player has spent in jail
     */
    public void incrementTurnsInJail()
    {
        TURNS_IN_JAIL++;
        System.out.println("You have been in Campo for " + TURNS_IN_JAIL + " turns.");
    }

    /**
     * gives the player a "Get out of Campo Free" card
     */
    public void giveGetOutOfCampoFreeCard()
    {
        GET_OUT_OF_CAMPO_CARD = true;
    }

    /**
     * plays the "Get out of Campo Free" card
     */
    public void getOutOfCampoFree()
    {
        if(GET_OUT_OF_CAMPO_CARD)
        {
            GET_OUT_OF_CAMPO_CARD = false;
            freePlayer();
        } else{
            System.out.println("Choose another option.");
        }
    }

    /**
     * freePlayer frees the player from Campo
     */
    public void freePlayer()
    {
        IS_FREE = true;
    }
    // Functions we need??:
            // Remove a property from a player's hand
            // Eliminate player??????

    /**
     * displayStats displays all of the current player's assets
     */
    public void displayStats()
    {
        System.out.println("Here are player " + PLAYER_NUM + "'s stats: ");
        //System.out.println("Name: " + NAME);
        System.out.println("Current Balance: " + ACCOUNT);
        System.out.println("Player is currently at square " + POSITION);
        if(!isFree())
            System.out.println("Player is currently in detention in Campo");
        if(HAND.isEmpty())
            System.out.println("Player does not currently own any properties");
        else{
            Property[] OwnedProperties = new Property[HAND.size()];
            OwnedProperties = HAND.toArray(OwnedProperties);
            System.out.println("THE PLAYER CURRENTLY OWNS " + OwnedProperties.length + " PROPERTIES: ");
            for(int i = 0; i < OwnedProperties.length; i++)
            {
                System.out.println("PROPERTY " + (i+1) + ": ");
                OwnedProperties[i].displayPropertyInfo();
            }
        }
        if(!OWNED_SETS.isEmpty())
        {
            Set[] OwnedSets = new Set[OWNED_SETS.size()];
            OwnedSets = OWNED_SETS.toArray(OwnedSets);
            System.out.println("THE PLAYER OWNS THE FOLLOWING SETS (every property in this set now has double its former rent)");
            for(int i = 0; i < OwnedSets.length; i++)
            {
                System.out.println("SET " + (i + 1) + ": " + OwnedSets[i].getSetName());
            }
        }
    }

    /**
     * getPlayerNum returns the number of the player
     * @return PLAYER_NUM
     */
    public int getPlayerNum()
    {
        return PLAYER_NUM;
    }

    /**
     * getPriority gets the player's priority
     * @return PLAYER_PRIORITY, the player's priority
     */
    public int getPriority()
    {
        return PLAYER_PRIORITY;
    }

    /**
     * getNumberOfRestaurants returns the number of restaurant properties that the player owns
     * @return RESTAURANTS_OWNED
     */
    public int getNumberOfRestaurants()
    {
        return RESTAURANTS_OWNED;
    }

    /**
     * getNumberOfUtilities returns the number of utility properties that the player owns
     * @return UTILITIES_OWNED
     */
    public int getNumberOfUtilities()
    {
        return UTILITIES_OWNED;
    }

    /**
     * getNumberOfHouses returns the number of houses that the player owns
     * @return number of houses owned
     */
    public int getNumberOfHouses()
    {
        return HOUSES_OWNED;
    }

    /**
     * getNumberOfHotels returns the number of hotels that the player owns
     * @return number of hotels owned
     */
    public int getNumberOfHotels()
    {
        return HOTELS_OWNED;
    }

    /**
     * getNumberOfSets returns the size of the OWNED_SETS array list
     * @return OWNED_SETS.size()
     */
    public int getNumberOfSets()
    {
        return OWNED_SETS.size();
    }
    /**
     * currentSquare returns the square on the board the player is currently at
     * @return POSITION
     */
    public int currentSquare()
    {
        return POSITION;
    }

    /**
     * getBalance returns the amount of money the player has in their account
     * @return ACCOUNT
     */
    public int getBalance()
    {
        return ACCOUNT;
    }

    /**
     * ownsNoSets checks to see if the player has any sets in his hand
     * @return true if he owns no sets
     */
    public boolean ownsNoSets()
    {
        if(OWNED_SETS.isEmpty())
            return true;
        return false;
    }

    /**
     * passedGo checks to see if the player has passed "Go"
     * @return PASSED_GO
     */
    public boolean passedGo()
    {
        return PASSED_GO;
    }

    /**
     * isFree checks if the player is not in Campo
     * @return IS_FREE
     */
    public boolean isFree()
    {
        if(TURNS_IN_JAIL == 4)
        {
            IS_FREE = true;
            TURNS_IN_JAIL = 0;
            ACCOUNT -= 50;
        }
        return IS_FREE;
    }

    /**
     * hasGetOutOfCampoFree checks to see if the player has the special card that allows them to get out of Campo for
     * free on any given turn in Campo
     * @return GET_OUT_OF_CAMPO_CARD
     */
    public boolean hasGetOutOfCampoFree()
    {
        return GET_OUT_OF_CAMPO_CARD;
    }

    /**
     * getName gets the name of the player
     * @return NAME, the player name
     */
    /*public String getName()
    {
        return NAME;
    }*/

    /**
     * getSet returns the desired set in the player's OWNED_SETS hand
     * @param s is the number of the set in OWNED_SETS
     * @return OWNED_SETS.get(s)
     * @throws SetNotFoundException
     */
    public Set getSet(int s) throws SetNotFoundException
    {
        if(!OWNED_SETS.isEmpty())
        {
            return OWNED_SETS.get(s - 1);
        } else{
            throw new SetNotFoundException("You do not own any sets.");
        }
    }
}
