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
    private int HOUSES_OWNED;
    private int HOTELS_OWNED;
    private int TURNS_IN_JAIL;
    private boolean PASSED_GO;
    private boolean IS_FREE;
    private String NAME;
    private ArrayList<Property> HAND;

    /**
     * Constructor for class Player
     * @param pNumber is the number of players
     */
    public Player(int pNumber, String pName)
    {
        PLAYER_NUM = pNumber;
        NAME = pName;
        POSITION = 0;
        ACCOUNT = 1500;
        HOUSES_OWNED = 0;
        HOTELS_OWNED = 0;
        TURNS_IN_JAIL = 0;
        PASSED_GO = false;
        IS_FREE = true;
        HAND = new ArrayList<>();
    }

    /**
     * clone() clones a Player
     * @return cloned
     * @throws CloneNotSupportedException
     */
    public Player clone() throws CloneNotSupportedException
    {
        try{
            Player cloned = (Player) super.clone();
            return cloned;
        } catch(CloneNotSupportedException E) {
            E.printStackTrace();
            return null;
        }
        //cloned.PLAYER_NUM = PLAYER_NUM;
        //cloned.PLAYER_PRIORITY = PLAYER_PRIORITY;
        //return null;
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
    // Functions we need:
            // Remove a property from a player's hand
            // Eliminate player??????

    /**
     * displayStats displays all of the current player's assets
     */
    public void displayStats()
    {
        System.out.println("Here are player " + PLAYER_NUM + "'s stats: ");
        System.out.println("Name: " + NAME);
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
     * currentSquare returns the square on the board the player is currently at
     * @return POSITION
     */
    public int currentSquare()
    {
        return POSITION;
    }

    /**
     * passedGo checks to see if the player has passed "Go"
     * @return true if the player has passed "Go"
     */
    public boolean passedGo()
    {
        if(PASSED_GO)
            return true;
        else
            return false;
    }

    /**
     * isFree checks if the player is not in Campo
     * @return IS_FREE
     */
    public boolean isFree()
    {
        if(TURNS_IN_JAIL == 3)
        {
            IS_FREE = true;
            TURNS_IN_JAIL = 0;
            ACCOUNT -= 50;
        }
        return IS_FREE;
    }

    /**
     * getName gets the name of the player
     * @return NAME, the player name
     */
    public String getName()
    {
        return NAME;
    }
}
