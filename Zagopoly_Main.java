/**
 * This program simulates a game of Zagopoly
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 03/28/2018
 */

import java.util.Scanner;

public class Zagopoly_Main
{
    /**
     * initPlayers initialiazes each player as a new Player and gets their names
     * @param players is the array holding the players of type Player
     */
    public static void initPlayers(Player[] players)
    {
        Scanner userIn = new Scanner(System.in);
        String nameString = "";
        for(int n = 0; n < players.length; n++)
        {
            System.out.println("Hello player " + (n + 1) + "! Please enter your name: ");
            nameString = userIn.next();
            players[n] = new Player(n + 1, nameString);
        }
    }

    /**
     * getPlayerPriority rolls the dice for each player to see who goes first
     * @param theDice are the game dice
     * @param players are the players
     */
    public static void setPlayerPriorities(ZagopolyDice theDice, Player[] players)
    {
        int[] startRollTotals = new int[players.length];
        for(int r = 0; r < players.length; r++)
        {
            System.out.printf(players[r].getName() + " is rolling the dice: ");
            theDice.rollDice();
            theDice.displayRoll();
            startRollTotals[r] = theDice.getTotalRoll();
            theDice.resetDice();
        }

        // set priorities according to rolls
        for(int r = 0; r < startRollTotals.length; r++)
        {
            players[r].setPriority(startRollTotals[r]);
            players[r].displayStats();
        }
    }

    /**
     * getNumberOfPlayers retrieves the desired number of players from the user
     * @return numberOfPlayers, the number of players
     */
    public static int getNumberOfPlayers()
    {
        int numberOfPlayers = 0;
        Scanner playerInput = new Scanner(System.in);
        numberOfPlayers = playerInput.nextInt();
        while(numberOfPlayers < 2 || numberOfPlayers > 4)
        {
            System.out.println("There must be a MINIMUM of 2 and a MAXIMUM of 4 players: ");
            numberOfPlayers = playerInput.nextInt();
        }
        return numberOfPlayers;
    }

    /**
     * getHighestPriority decides which player will have the first turn
     * @param players is the array containing the players in the game
     * @return the number of the player with the highest priority
     */
    public static int getHighestPriority(Player[] players)
    {
        int largestPriority = 0;
        int playerWithPriority = 0;
        for(int p = 0; p < players.length; p++)
        {
            if(players[p].getPriority() > largestPriority)
            {
                largestPriority = players[p].getPriority();
                playerWithPriority = p;
            }
        }
        return playerWithPriority;
    }

    /**
     * main program
     * @param args
     */
    public static void main(String[] args) throws CloneNotSupportedException
    {
        char playAgain = 'y';
        while(playAgain == 'y')
        {
            System.out.printf("Hello! Welcome to Zag-opoly. Select the number of players to begin the game. ");

            int numberOfPlayers = getNumberOfPlayers();                 // get number of players
            ZagopolyDice theDice = new ZagopolyDice(2);         // create the dice
            Player[] players = new Player[numberOfPlayers];             // create an array of players
            initPlayers(players);                                       // initialize each player and get their names
            setPlayerPriorities(theDice, players);                      // find out which player will go first
            int currentPlayer = getHighestPriority(players);              // the player with the highest roll goes first

            System.out.println("Here is the player that will go first: player " + (currentPlayer + 1) + ", "
                    + players[currentPlayer].getName());
            Board gameBoard = new Board(players);                       // create the game board full of pre-determined properties

            System.out.println();
            Scanner userIn = new Scanner(System.in);                  // dice roll button (change this when we get to board setup)
            String roll;
            boolean gameHasEnded = false;

            //************** the game can now begin
            while(!gameHasEnded)
            {
                if(players[currentPlayer].isFree())
                {
                    int rolls = 0;
                    boolean goAgain = true;
                    while(goAgain)
                    {
                        if(rolls == 3)
                        {
                            goAgain = false;
                            System.out.println("You are getting too lucky, go to Campo.");
                            players[currentPlayer].imprisonPlayer();
                        }
                        System.out.println("It is " + players[currentPlayer].getName() + "'s turn, please roll the dice: " +
                                "enter 'r' to do so: ");
                        roll = userIn.next();
                        while(roll.charAt(0) != 'r')
                        {
                            System.out.println("Enter 'r' to roll the dice.");
                            roll = userIn.next();
                        }
                        // Roll
                        theDice.rollDice();
                        theDice.displayRoll();
                        players[currentPlayer].movePlayer(theDice.getTotalRoll());
                        //players[currentPlayer].movePlayer(30);


                        // Display info of square that was landed on
                        Property currentSquare = gameBoard.getSquare(players[currentPlayer].currentSquare());
                        System.out.println("THE PLAYER HAS LANDED ON A PROPERTY WITH THE FOLLOWING INFO:");
                        System.out.println("You have landed on " + currentSquare.getName());
                        if(currentSquare.isForSale())               // case that the property can actually be bought
                        {
                            String buyOption;
                            System.out.println("What would you like to do (enter letter of option to select)?");
                            System.out.println("Option A: Buy Property, property is $" + currentSquare.getPrice());
                            System.out.println("Option B: Pass");
                            buyOption = userIn.next();
                            if(buyOption.charAt(0) == 'A' || buyOption.charAt(0) == 'a')
                            {
                                int cost = currentSquare.getPrice();
                                players[currentPlayer].finePlayer(cost);
                                currentSquare.addOwner(players[currentPlayer]);
                                players[currentPlayer].takeOwnershipOfProperty(currentSquare);
                                System.out.println("You now own this property! Other players who land here will now have to" +
                                        " pay you rent.");
                                players[currentPlayer].displayStats();
                            } else{
                                System.out.println("You have passed this property, it will remain unowned.");
                            }
                        }else{
                            System.out.println("This square is not a property");
                            if(currentSquare.isCardSquare())
                            {
                                System.out.println("You will receive the following card: ");
                                currentSquare.getServiceCard(players[currentPlayer]);
                                if(players[currentPlayer].passedGo())
                                {
                                    currentSquare = gameBoard.getSquare(0);
                                    players[currentPlayer].payPlayer(200);
                                }
                            }

                            if(currentSquare.isTaxSquare())
                            {
                                System.out.println("You must pay $" + currentSquare.getRent());
                                players[currentPlayer].finePlayer(currentSquare.getRent());
                                players[currentPlayer].displayStats();
                            }

                            if(currentSquare.isGoToCampoSquare())
                            {
                                System.out.println("Go directly to Campo. Do not pass go, do not collect $200.");
                                players[currentPlayer].imprisonPlayer();
                            }
                        }
                        players[currentPlayer].displayStats();

                        theDice.resetDice();
                        rolls++;
                        goAgain = false;
                        if(theDice.doubles())
                            goAgain = true;
                    }
                } else{
                    System.out.println("You are in jail, so no action may be taken this turn.");
                    players[currentPlayer].incrementTurnsInJail();
                }

                if(currentPlayer < players.length - 1)
                    currentPlayer++;
                else
                    currentPlayer = 0;
            }
            playAgain = 'n';
        }
    }
}
