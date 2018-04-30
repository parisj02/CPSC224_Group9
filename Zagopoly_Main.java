/**
 * This program simulates a game of Zagopoly
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 03/28/2018
 */

import java.util.Scanner;
import javax.swing.*;

public class Zagopoly_Main
{
    /**
     * delay delays the program
     */
    public static void delay()
    {
        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
            System.out.println("Continue");
        }
    }

    /**
     * initPlayers initialiazes each player as a new Player and gets their names
     * @param players is the array holding the players of type Player
     */
    public static void initPlayers(Player[] players)
    {
        //Scanner userIn = new Scanner(System.in);
        //String nameString = "";
        for(int n = 0; n < players.length; n++)
        {
            //System.out.println("Hello player " + (n + 1) + "! Please enter your name: ");
            //nameString = userIn.next();
            players[n] = new Player(n + 1);
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
            System.out.printf(players[r].getPlayerNum() + " is rolling the dice: ");
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
     * postRollOptions gives the player his/her post roll options
     * @param currentPlayer is the number of the player who is currently going
     * @param players is the array of players in the game
     * @param theDice is the game dice
     */
    public static void postRollOptions(int currentPlayer, Player players[], ZagopolyDice theDice)
    {
        // Post Roll Options
        char option = 's';
        Scanner userIn = new Scanner(System.in);
        String optionString = "";
        while(option != 'E')    // n for next player's turn
        {
            System.out.println("You are done rolling for this turn. What would you like to do?");
            System.out.println("Option A: Buy a house or hotel for a certain property.");
            //System.out.println("Option M");
            System.out.println("Option E: End your turn so the next player can roll.");
            optionString = userIn.next();
            option = optionString.charAt(0);
            while(option < 'A' || option > 'E')
            {
                System.out.println("Invalid input. The inputs here must be uppercase.");
                optionString = userIn.next();
                option = optionString.charAt(0);
            }

            if(option == 'A')      // case that the player wants to buy a house
            {
                if(players[currentPlayer].ownsNoSets()) // case that the player doesn't own a set
                {
                    System.out.println("You cannot add any houses because you do not own any sets.");
                } else{ // case that the player owns 1 or more sets
                    System.out.println("You have chosen to buy a house. Enter the set number of the " +
                            "property you want to add to. The number you must enter will be the set comes " +
                            "first IN YOUR COLLECTION OF SETS.");
                    int setNumber = userIn.nextInt();
                    while(setNumber > players[currentPlayer].getNumberOfSets()) // get setNumber
                    {
                        System.out.println("Invalid set number. Try again.");
                        setNumber = userIn.nextInt();
                    }

                    try{    // if the player enters a valid set number this code should run
                        Set currentSet = players[currentPlayer].getSet(setNumber);
                        System.out.println("Choose which property you want to add a house to." +
                                "Enter 1 for the property in the set that comes first on the board," +
                                "2 for the second property, and 3 for the third (if there is a third)");
                        int propertyNum = userIn.nextInt();

                        while(propertyNum > currentSet.setSize()) // get propertyNum
                        {
                            System.out.println("Invalid index. Try again.");
                            propertyNum = userIn.nextInt();
                        }

                        Property currentSquare = currentSet.getProperty(propertyNum);
                        if(players[currentPlayer].getBalance() > currentSquare.getHousePrice())
                        { // case that the player has the money for a house/hotel
                            if(currentSquare.getNumHouses() < 4) // case that there are not yet four houses on property
                            {
                                players[currentPlayer].finePlayer(currentSquare.getHousePrice());
                                currentSquare.addHouse();
                                currentSquare.updateRent(theDice);
                                players[currentPlayer].buyHouse();
                            }
                            else if(currentSquare.getNumHouses() == 4 && !currentSquare.hasHotel())
                            { // case that there are four houses on the property (add a hotel)
                                players[currentPlayer].finePlayer(currentSquare.getHousePrice());
                                currentSquare.addHotel();
                                currentSquare.updateRent(theDice);
                                players[currentPlayer].buyHotel();
                            }
                            else{ // case that there is a hotel on the property
                                System.out.println("This property's rent has already been maxed.");
                            }
                        } else{ // case that the player doesn't have the money for a house/hotel
                            System.out.println("You cannot afford to add a house to this property.");
                        }
                    } catch(SetNotFoundException S){ // if the player owns no sets this should occur
                        S.printStackTrace();
                    }
                }
            }
            if(option == 'E') // case that the player wants to end his/her turn
            {
                System.out.println("You have chosen to end this turn. It is now the next player's turn.");
            }
        }
    }

    /**
     * getNumberOfPlayers retrieves the desired number of players from the user
     * @return numberOfPlayers, the number of players
     */
    public static int getNumberOfPlayers()
    {
        GetPlayerUI pgui = new GetPlayerUI();
        int numberOfPlayers = 0;
        while(numberOfPlayers == 0)
        {
            System.out.printf("");
            numberOfPlayers = pgui.getNumPlayers();
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
     * createIconArray creates the icons that will be displayed on the board during the game
     * @param numberOfPlayers is the number of players in the game
     * @return iconArray
     */
    public static PlayerIcon[] createIconArray(int numberOfPlayers){
        PlayerIcon[] iconArray = new PlayerIcon[numberOfPlayers];
        int count = 0;
        for(int i = 0; i <= numberOfPlayers - 1; i++){
            PlayerIcon currentIcon = new PlayerIcon(count);
            iconArray[i] = currentIcon;
            count++;
        }
        return iconArray;
    }

    /**
     * main program
     * @param args
     */
    public static void main(String[] args) throws SetNotFoundException
    {
        char playAgain = 'y';               // play again character
        while(playAgain == 'y')             // the Zagopoly program loop
        {
            // startup procedures
            int numberOfPlayers = getNumberOfPlayers();                 // get number of players
            Player[] players = new Player[numberOfPlayers];             // create an array of players
            PlayerIcon[] iconArray = createIconArray(numberOfPlayers);
            ZagopolyDice theDice = new ZagopolyDice(2);         // create the dice
            GameBoardUI gui = new GameBoardUI(iconArray, numberOfPlayers);

            MainMenu Menu = new MainMenu(gui);
            initPlayers(players);                                       // initialize each player and get their names
            ZagopolyTextWindow TextWindow = new ZagopolyTextWindow();
            setPlayerPriorities(theDice, players);                      // find out which player will go first
            int currentPlayer = getHighestPriority(players);              // the player with the highest roll goes first

            TextWindow.printMessage("Here is the player that will go first: Player " + (currentPlayer + 1));
            //System.out.println("Here is the player that will go first: Player " + (currentPlayer + 1));
            Board gameBoard = new Board(players);                       // create the game board full of pre-determined properties
            The_Sets gameSets = new The_Sets(gameBoard);


            System.out.println();
            Scanner userIn = new Scanner(System.in);                  // dice roll button (change this when we get to board setup)
            boolean gameHasEnded = false;

            //************** the game can now begin
            while(!gameHasEnded)                // the Zagopoly game loop
            {
                if(players[currentPlayer].isFree())     // case that the current player is not in campo during their turn
                {
                    gui.revealDice();
                    int rolls = 0;
                    boolean goAgain = true;
                    while(goAgain)
                    {
                        if(rolls == 3)              // case that the player has rolled 3 doubles in a row
                        {
                            goAgain = false;
                            System.out.println("You are getting too lucky, go to Campo.");
                            players[currentPlayer].imprisonPlayer();
                        }
                        System.out.println("It is Player " + players[currentPlayer].getPlayerNum() + "'s turn, please roll the dice: " +
                                "click 'roll' to do so: ");
                        int roll = 0;
                        gui.resetRoll();
                        delay();

                        // Roll
                        while(roll == 0)
                        {
                            System.out.printf("");
                            roll = gui.getRoll();
                        }
                        players[currentPlayer].movePlayer(roll);
                        gui.step(gameBoard, players[currentPlayer], currentPlayer);

                        /*System.out.println("FOR TESTING --> ENTER SQUARE YOU WANT TO MOVE TO: ");
                        Scanner movement = new Scanner(System.in);
                        int newSquare = movement.nextInt();
                        players[currentPlayer].relocatePlayer(newSquare);
                        gui.step(gameBoard, players[currentPlayer], currentPlayer);*/

                        if(players[currentPlayer].passedGo()) // case that the player moved past "Go"
                        {
                            System.out.println("You have passed Go! Collect $200!!");
                            players[currentPlayer].payPlayer(200);
                        }

                        delay();
                        // Display info of square that was landed on
                        Property currentSquare = gameBoard.getSquare(players[currentPlayer].currentSquare());
                        System.out.println("THE PLAYER HAS LANDED ON A PROPERTY WITH THE FOLLOWING INFO:");
                        System.out.println("You have landed on " + currentSquare.getName());
                        if(currentSquare.isForSale())               // case that the property can actually be bought
                        {
                            if(!currentSquare.isOwned())            // case that the property is not owned
                            {
                                String buyOption;
                                System.out.println("What would you like to do (enter letter of option to select)?");
                                System.out.println("Option A: Buy Property, property is $" + currentSquare.getPrice());
                                System.out.println("Option B: Pass");
                                buyOption = userIn.next();
                                if(buyOption.charAt(0) == 'A' || buyOption.charAt(0) == 'a') // player wants to buy
                                {
                                    int cost = currentSquare.getPrice();
                                    if(players[currentPlayer].getBalance() < currentSquare.getPrice())
                                    { // case that the player does not have the money for this property
                                        System.out.println("Insufficient funds to buy this property.");
                                    } else{ // case that the player does have the money for this property
                                        players[currentPlayer].finePlayer(cost);
                                        currentSquare.addOwner(players[currentPlayer]);
                                        players[currentPlayer].takeOwnershipOfProperty(currentSquare);
                                        if(currentSquare.isUtility()) // case that the current property is a utility
                                        {
                                            players[currentPlayer].addUtility();
                                        }
                                         else if(currentSquare.isRestaurant())    // case that the property is a restaurant
                                        {
                                            players[currentPlayer].addRestaurant();
                                            players[currentPlayer].updateRestaurantRents(theDice);
                                        } else{ // case that the property is a primary property (and is therefore in a set)
                                            try
                                            {
                                                int currentSetNumber = currentSquare.getSetNumber(gameSets);
                                                Set currentSet = gameSets.getSet(currentSetNumber);
                                                currentSet.addOwner(players[currentPlayer]);
                                                players[currentPlayer].takeOwnerShipOfSet(currentSet);
                                                currentSet.updateRents(theDice);
                                            }catch(SetNotFoundException S)
                                            {
                                                S.printStackTrace();
                                            }
                                        }
                                        System.out.println("You now own this property! Other players who land here " +
                                                "will now have to pay you rent.");
                                    }
                                    players[currentPlayer].displayStats();
                                } else{                             // case that player doesn't want to buy
                                    System.out.println("You have passed this property, it will remain unowned.");
                                }
                            } else{                      // case that the property is owned
                                if(currentSquare.getOwner() == players[currentPlayer]) // case that this is your property
                                {
                                    System.out.println("You hava landed on one of your own properties! " +
                                            "Isn't life sweet when you're rich? :)");
                                } else{ // case that this isn't your property
                                    System.out.println("This property is owned by " + currentSquare.getOwnerNum());
                                    if(currentSquare.isUtility()) // case that the property is a utility
                                    {
                                        System.out.println("You must pay Player " + currentSquare.getOwnerNum() + " 4x your roll if " +
                                                "the owner owns just this utility, and 10x your roll if the owner owns " +
                                                "both utilities.");
                                        currentSquare.updateRent(theDice);
                                    }
                                    else if(currentSquare.isRestaurant()) // case that the property is a restaurant
                                    {
                                        System.out.println("You must pay Player " + currentSquare.getOwnerNum() +
                                                " $50 if he/she owns 1 restaurant, $100 if he/she owns 2, $150 if he/she owns" +
                                                "3, and $200 if he/she owns all 4.");
                                    }else{          // case that the property is a primary property (part of a color set)
                                        System.out.println("You must pay Player " + currentSquare.getOwnerNum() + " $" +
                                                currentSquare.getRent() + ": rent is double if the owner owns the set.");
                                    }
                                    if(players[currentPlayer].getBalance() < currentSquare.getRent())
                                    {   // case that the player cannot pay the rent due to insufficient funds
                                        System.out.println("You do not have enough money in your account to pay " +
                                                "the owner of this property the desired rent.");
                                    } else{  // case that the player can pay the rent
                                        currentSquare.payOwnerRent();
                                        players[currentPlayer].finePlayer(currentSquare.getRent());
                                        System.out.println("You have paid Player " + currentSquare.getOwnerNum() + " $"
                                                + currentSquare.getRent());
                                    }
                                }
                            }
                        }else{ // case that the property cannot be bought
                            System.out.println("This square is not a property");
                            if(currentSquare.isCardSquare()) // case that the current square is a card square
                            {
                                System.out.println("You will receive the following card: ");
                                if(currentSquare.getNumber() == 2 || currentSquare.getNumber() == 17 ||
                                        currentSquare.getNumber() == 33) // case of community service square
                                {
                                    currentSquare.getServiceCard(players[currentPlayer], gameBoard, gui, currentPlayer);
                                }
                                if(currentSquare.getNumber() == 7 || currentSquare.getNumber() == 22 ||
                                        currentSquare.getNumber() == 36) // case of chance square
                                {
                                    currentSquare.getChanceCard(players[currentPlayer], gameBoard, gui, currentPlayer);
                                }
                                if(players[currentPlayer].passedGo()) // case that the special card moves player past Go
                                {
                                    currentSquare = gameBoard.getSquare(0);
                                    players[currentPlayer].payPlayer(200);
                                }
                            }
                            if(currentSquare.isTaxSquare()) // case that the current square is a tax sqaure
                            {
                                System.out.println("You must pay $" + currentSquare.getRent());
                                players[currentPlayer].finePlayer(currentSquare.getRent());
                                players[currentPlayer].displayStats();
                            }
                            if(currentSquare.isCampoSquare()) // case that the current square is the Campo Square
                            {
                                System.out.println("You are just visiting Campo, so you won't be imprisoned. :)");
                            }
                            if(currentSquare.isGoToCampoSquare()) // case that the current square is "Go To Campo" Square
                            {
                                System.out.println("Go directly to Campo. Do not pass go, do not collect $200.");
                                players[currentPlayer].imprisonPlayer();
                                gui.step(gameBoard, players[currentPlayer], currentPlayer);
                                theDice.resetDice();
                            }
                        }
                        theDice.resetDice();    // set total roll back to 0
                        rolls++;                // increment number of times the player has rolled
                        goAgain = false;
                        if(theDice.doubles())      // case that the player rolled doubles
                            goAgain = true;
                    }

                    // Post Roll Options
                    postRollOptions(currentPlayer, players, theDice);
                    players[currentPlayer].displayStats();

                } else{     // case that the player is in Campo
                    System.out.println("Player " + players[currentPlayer].getPlayerNum() +
                            ", you are in Campo, so you have 4 choices.");
                    System.out.println("Option 1: Remain in Campo (you will only remain here for a max of 3 turns," +
                            " at which point you will have to pay $50).");
                    System.out.println("Option 2: Pay $50 and get out of Campo now.");
                    System.out.println("Option 3: Try to roll a doubles to get out of Campo for free.");
                    System.out.println("Option 4: Play your 'Get out of Campo Free' card.");
                    boolean done = false;

                    while(!done) // while the player is still mulling over their options in Campo
                    {
                        int option = userIn.nextInt();
                        while(option < 1 || option > 4)    // obtain player's choice
                        {
                            System.out.println("Invalid option. Try again.");
                            option = userIn.nextInt();
                        }

                        if(option == 1) // case that the player chooses to remain in Campo
                        {
                            System.out.println("You have chosen to remain in Campo for this turn. " +
                                    "If you have spent 3 turns in Campo, however, you will automatically be freed.");
                            players[currentPlayer].incrementTurnsInJail();
                            done = true;
                        }
                        else if(option == 2) // case that player pays $50 to get out now
                        {
                            System.out.println("You have chosen to pay $50 to get out of jail now.");
                            players[currentPlayer].finePlayer(50);
                            players[currentPlayer].freePlayer();
                            done = true;
                        }
                        else if(option == 3)    // case that the player wants to try to roll a doubles to get out now for free
                        {
                            System.out.println("You have chosen to try to get lucky. Good luck.");
                            gui.revealDice();
                            int roll = 0;
                            boolean isDoubles = false;
                            delay();

                            while(roll == 0)
                            {
                                System.out.printf("");
                                roll = gui.getRoll();
                                isDoubles = gui.getDoubles();
                            }
                            delay();

                            if(isDoubles) // case that they successfully roll doubles (don't move, just make it their
                            // turn again with IS_FREE set to true)
                            {
                                System.out.println("You got lucky. :)");
                                players[currentPlayer].freePlayer();
                            } else{ // case that the player doesn't roll doubles
                                System.out.println("Better luck next time. :(");
                                players[currentPlayer].incrementTurnsInJail();
                            }
                            gui.resetRoll();
                            done = true;
                        } else{
                            System.out.println("You have chosen to play your 'Get out of Campo Free' card.");
                            if(players[currentPlayer].hasGetOutOfCampoFree())
                            {
                                System.out.println("Very well, you will be freed.");
                                done = true;
                            }else{
                                System.out.println("You do not have this card. Choose a different option.");
                            }
                            players[currentPlayer].getOutOfCampoFree();
                        }
                    }

                    if(players[currentPlayer].isFree()) // case that the player is now free
                    {
                        System.out.println("YOU ARE NOW FREE FROM CAMPO!!!! You will be given all the options" +
                                " of a normal turn.");
                        currentPlayer--;
                        if(currentPlayer < 0)   // case that currentPlayer is decremented below player 1 (goes to
                            // player with highest number). The purpose of this is to make the game return the turn
                            // the current player after this loop ends
                            currentPlayer = players.length - 1;
                    } else{
                        postRollOptions(currentPlayer, players, theDice);
                        players[currentPlayer].displayStats();
                    }
                }

                if(currentPlayer < players.length - 1) // it is now the next player's turn
                    currentPlayer++;
                else
                    currentPlayer = 0;
            }
            playAgain = 'n';
        }
    }
}
