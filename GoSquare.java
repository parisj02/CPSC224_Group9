/**
 * This class is a child class of class property that creates the game's "Go" Square
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/11/2018
 */

public class GoSquare extends Property
{
    private Player[] OWNERS;

    /**
     * constructor for class GoSquare
     * @param players is the array containing all of the players in the game
     */
    public GoSquare(Player players[], int XCoord, int YCoord)
    {
        super(0, 0, 0, XCoord, YCoord);
        this.OWNERS = new Player[players.length];
        initOwners(players);
    }

    /**
     * displayPropertyInfo overrides displayPropertyInfo of class Property;
     * only displays "The Go Square"
     */
    public void displayPropertyInfo()
    {
        System.out.println("The Go Square");
    }

    /**
     * initPlayerX initializes a new player as an owner of "Go" (all players own "Go")
     * @param players is the array containing the players in the game
     */
    private void initOwners(Player players[])
    {
        for(int i = 0; i < OWNERS.length; i++)
        {
            this.OWNERS[i] = players[i];
        }
    }
}
