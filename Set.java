/**
 * This class creates a small array of primary properties called a "Set"
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/23/2018
 */

public class Set
{
    protected String NAME;
    protected Property SET[];
    protected Player OWNER;

    /**
     * constructor for class Set
     * @param setSize is the size of the property set
     */
    public Set(int setSize)
    {
        SET = new Property[setSize];
        NAME = "Undefined Set";
    }

    /**
     * addOwner makes a player the owner of this set of properties if he owns all three
     * @param newOwner is the player that is potentially going to be the new owner of the set
     */
    public void addOwner(Player newOwner)
    {
        boolean ownerIsTheSame = true;
        for(int i = 0; i < SET.length; i++)
        {
            if(SET[i].getOwner() != newOwner)
            {
                ownerIsTheSame = false;
            }
        }

        if(ownerIsTheSame)
            OWNER = newOwner;
        else
            System.out.println("Player has some properties in this set, but has not yet acquired ALL of the " +
                    "properties in this set.");
    }

    /**
     * displaySetInfo displays the names of the properties in the set
     */
    public void displaySetInfo()
    {
        System.out.println("This is the " + NAME + ", it contains: ");
        for(int i = 0; i < SET.length; i++)
        {
            System.out.printf(SET[i].getName());
            if(i != (SET.length - 1))
                System.out.printf(" and ");
        }
        System.out.println("");
    }

    /**
     * updateRents updates all the rents of the properties in the set
     */
    public void updateRents(ZagopolyDice theDice)
    {
        if(isOwned())
        {
            for(int p = 0; p < SET.length; p++)
            {
                SET[p].updateRent(theDice);
            }
        }
    }

    /**
     * setSize returns the size of the set
     * @return size
     */
    public int setSize()
    {
        int size = SET.length;
        return size;
    }

    /**
     * isOwned checks to see if the current set is owned
     * @return false if OWNER is null
     */
    public boolean isOwned()
    {
        if(OWNER == null)
            return false;
        return true;
    }

    /**
     * containsProperty checks to see if the set contains a particular property
     * @param P is the property in question
     * @return true if the set contains this property
     */
    public boolean containsProperty(Property P)
    {
        boolean contains = false;
        int p = 0;
        while(!contains && p < SET.length)
        {
            if(SET[p].equals(P))
            {
                contains = true;
            }
            p++;
        }

        if(contains)
        {
            return true;
        }
        return false;
    }

    /**
     * getSetName returns the name of the set
     * @return NAME
     */
    public String getSetName()
    {
        return NAME;
    }

    /**
     * getProperty returns the property at the desired position in the set
     * p is the location of the property in the set
     * @return SET[p - 1]
     */
    public Property getProperty(int p)
    {
        return SET[p - 1];
    }
}
