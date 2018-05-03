/**
 * This program simulates a game of Zagopoly
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 03/28/2018
 */

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
     * initPlayers initializes each player as a new Player and gets their names
     * @param players is the array holding the players of type Player
     * @param TextWindow is the GUI that displays the game messages
     */
    public static void initPlayers(Player[] players, ZagopolyTextWindow TextWindow)
    {
        for(int n = 0; n < players.length; n++)
        {
            players[n] = new Player(n + 1, TextWindow);
        }
    }

    /**
     * getPlayerPriority rolls the dice for each player to see who goes first
     * @param theDice are the game dice
     * @param players are the players
     * @param TextWindow is the GUI that displays the game messages
     */
    public static void setPlayerPriorities(ZagopolyDice theDice, Player[] players, ZagopolyTextWindow TextWindow)
    {
        int[] startRollTotals = new int[players.length];
        for(int r = 0; r < players.length; r++)
        {
            TextWindow.printMessage("Player " + players[r].getPlayerNum() + " is rolling the dice: ");
            theDice.rollDice();
            startRollTotals[r] = theDice.getTotalRoll();
            theDice.resetDice();
        }

        // set priorities according to rolls
        for(int r = 0; r < startRollTotals.length; r++)
        {
            players[r].setPriority(startRollTotals[r]);
        }
    }

    /**
     * postRollOptions gives the player his/her post roll options
     * @param currentPlayer is the number of the player who is currently going
     * @param players is the array of players in the game
     * @param theDice is the game dice
     * @param TextWindow is the GUI that displays the game messages
     */
    public static void postRollOptions(int currentPlayer, Player players[], ZagopolyDice theDice, ZagopolyTextWindow TextWindow)
    {
        // Post Roll Options
        char option = '0';
        while(option != 'E')    // n for next player's turn
        {
            TextWindow.printMessage("You are done rolling for this turn. What would you like to do?");
            TextWindow.printMessage("Option D: Buy a house or hotel for a certain property.");
            TextWindow.printMessage("Option E: End your turn so the next player can roll.");
            option = TextWindow.getChar();

            while(option < 'D' || option > 'E')
            {
                System.out.printf("");
                option = TextWindow.getChar();
            }

            if(option == 'D')      // case that the player wants to buy a house
            {
                if(players[currentPlayer].ownsNoSets()) // case that the player doesn't own a set
                {
                    TextWindow.printMessage("You cannot add any houses because you do not own any sets.");
                } else{ // case that the player owns 1 or more sets
                    TextWindow.printMessage("You have chosen to buy a house. Enter the set number of the " +
                            "property you want to add to. The number you must enter will be the set comes " +
                            "first IN YOUR COLLECTION OF SETS.");
                    int setNumber = TextWindow.getChar();
                    while(setNumber > players[currentPlayer].getNumberOfSets()) // get setNumber
                    {
                        System.out.printf("");
                        setNumber = TextWindow.getChar();
                    }

                    try{    // if the player enters a valid set number this code should run
                        Set currentSet = players[currentPlayer].getSet(setNumber);
                        TextWindow.printMessage("Choose which property you want to add a house to." +
                                "Enter 1 for the property in the set that comes first on the board," +
                                "2 for the second property, and 3 for the third (if there is a third)");
                        int propertyNum = TextWindow.getChar();

                        while(propertyNum > currentSet.setSize()) // get propertyNum
                        {
                            System.out.printf("");
                            propertyNum = TextWindow.getChar();
                        }

                        Property currentSquare = currentSet.getProperty(propertyNum);
                        if(players[currentPlayer].getBalance() > currentSquare.getHousePrice())
                        { // case that the player has the money for a house/hotel
                            if(currentSquare.getNumHouses() < 4) // case that there are not yet four houses on property
                            {
                                players[currentPlayer].finePlayer(currentSquare.getHousePrice());
                                currentSquare.addHouse(TextWindow);
                                currentSquare.updateRent(theDice);
                                players[currentPlayer].buyHouse();
                            }
                            else if(currentSquare.getNumHouses() == 4 && !currentSquare.hasHotel())
                            { // case that there are four houses on the property (add a hotel)
                                players[currentPlayer].finePlayer(currentSquare.getHousePrice());
                                currentSquare.addHotel(TextWindow);
                                currentSquare.updateRent(theDice);
                                players[currentPlayer].buyHotel();
                            }
                            else{ // case that there is a hotel on the property
                                TextWindow.printMessage("This property's rent has already been maxed.");
                            }
                        } else{ // case that the player doesn't have the money for a house/hotel
                            TextWindow.printMessage("You cannot afford to add a house to this property.");
                        }
                    } catch(SetNotFoundException S){ // if the player owns no sets this should occur
                        S.printStackTrace();
                    }
                }
            }
            if(option == 'E') // case that the player wants to end his/her turn
            {
                TextWindow.printMessage("You have chosen to end this turn. It is now the next player's turn.");
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
    public static PlayerIcon[] createIconArray(int numberOfPlayers)
    {
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
        // startup procedures
        ZagopolyTextWindow TextWindow = new ZagopolyTextWindow();
        int numberOfPlayers = getNumberOfPlayers();                 // get number of players
        Player[] players = new Player[numberOfPlayers];             // create an array of players
        initPlayers(players, TextWindow);
        PlayerIcon[] iconArray = createIconArray(numberOfPlayers);
        ZagopolyDice theDice = new ZagopolyDice(2);         // create the dice
        GameBoardUI gui = new GameBoardUI(iconArray, numberOfPlayers, TextWindow);

        MainMenu Menu = new MainMenu(gui);

        setPlayerPriorities(theDice, players, TextWindow);                      // find out which player will go first
        int currentPlayer = getHighestPriority(players);              // the player with the highest roll goes first

        TextWindow.printMessage("Here is the player that will go first: Player " + (currentPlayer + 1) + ". " +
                "Please click 'start'.");
        Board gameBoard = new Board(players);                       // create the game board full of pre-determined properties
        The_Sets gameSets = new The_Sets(gameBoard);


        TextWindow.printMessage("");
        boolean gameHasEnded = false;

        //************** the game can now begin
        while(!gameHasEnded)                // the Zagopoly game loop
        {
            TextWindow.resetUserInput();
            if(!players[currentPlayer].isEliminated()) // case that this player is actually in the game
            {
                if(players[currentPlayer].isFree())     // case that the current player is not in campo during their turn
                {
                    int doublesRolled = 0;
                    boolean goAgain = true;
                    while(goAgain)
                    {
                        gui.revealDice();

                        TextWindow.printMessage("It is Player " + players[currentPlayer].getPlayerNum() + "'s turn, please " +
                                "roll the dice: click 'roll' to do so. ");
                            int roll = 0;
                            gui.resetRoll();
                            delay();

                            // Roll
                            while(roll == 0)
                            {
                                System.out.printf("");
                                roll = gui.getRoll();
                            }
                            boolean doubles = gui.getDoubles();
                            if(doubles)
                                doublesRolled++;

                        if(doublesRolled == 3)   // case that the player has rolled 3 doubles in a row
                        {
                            goAgain = false;
                            TextWindow.printMessage("You are getting too lucky, go to Campo.");
                            players[currentPlayer].imprisonPlayer();
                        } else{ // case that this is not the third doubles in a row
                            players[currentPlayer].movePlayer(roll);
                            gui.step(gameBoard, players[currentPlayer], currentPlayer);

                            if(players[currentPlayer].passedGo()) // case that the player moved past "Go"
                            {
                                TextWindow.printMessage("You have passed Go! Collect $200!!");
                                players[currentPlayer].payPlayer(200);
                            }

                            delay();
                            // Display info of square that was landed on
                            Property currentSquare = gameBoard.getSquare(players[currentPlayer].currentSquare());
                            TextWindow.printMessage("YOU HAVE LANDED ON " + currentSquare.getName());
                            if(currentSquare.isForSale())               // case that the property can actually be bought
                            {
                                if(!currentSquare.isOwned())            // case that the property is not owned
                                {
                                    currentSquare.addOwner(players[currentPlayer], theDice, gameSets, TextWindow);
                                } else{                      // case that the property is owned
                                    if(currentSquare.getOwner() == players[currentPlayer]) // case that this is your property
                                    {
                                        TextWindow.printMessage("You have landed on one of your own properties! " +
                                                "Isn't life sweet when you're rich? :)");
                                    } else{ // case that this isn't your property
                                        TextWindow.printMessage("This property is owned by Player " + currentSquare.getOwnerNum());
                                        if(currentSquare.isUtility()) // case that the property is a utility
                                        {
                                            TextWindow.printMessage("You must pay Player " + currentSquare.getOwnerNum() + " 4x your roll if " +
                                                    "the owner owns just this utility, and 10x your roll if the owner owns " +
                                                    "both utilities.");
                                            theDice = gui.getDice();
                                            currentSquare.updateRent(theDice);
                                        }
                                        else if(currentSquare.isRestaurant()) // case that the property is a restaurant
                                        {
                                            TextWindow.printMessage("You must pay Player " + currentSquare.getOwnerNum() +
                                                    " $50 if he/she owns 1 restaurant, $100 if he/she owns 2, $150 if he/she owns" +
                                                    "3, and $200 if he/she owns all 4.");
                                        }else{          // case that the property is a primary property (part of a color set)
                                            TextWindow.printMessage("You must pay Player " + currentSquare.getOwnerNum() + " $" +
                                                    currentSquare.getRent() + ": rent is double if the owner owns the set.");
                                        }
                                        if(players[currentPlayer].getBalance() < currentSquare.getRent())
                                        {   // case that the player cannot pay the rent due to insufficient funds
                                            TextWindow.printMessage("You do not have enough money in your account to pay " +
                                                    "the owner of this property the desired rent.");
                                            currentSquare.payOwnerRent();
                                            players[currentPlayer].finePlayer(players[currentPlayer].getBalance());
                                            // drains the current player's account
                                            players[currentPlayer].eliminatePlayer();
                                        } else{  // case that the player can pay the rent
                                            currentSquare.payOwnerRent();
                                            players[currentPlayer].finePlayer(currentSquare.getRent());
                                            TextWindow.printMessage("You have paid Player " + currentSquare.getOwnerNum() + " $"
                                                    + currentSquare.getRent());
                                        }
                                    }
                                }
                            }else{ // case that the property cannot be bought
                                TextWindow.printMessage("This square is not a property");
                                if(currentSquare.isCardSquare()) // case that the current square is a card square
                                {
                                    TextWindow.printMessage("You will receive the following card: ");
                                    if(currentSquare.getNumber() == 2 || currentSquare.getNumber() == 17 ||
                                            currentSquare.getNumber() == 33) // case of community service square
                                    {
                                        currentSquare.getServiceCard(players[currentPlayer], gameBoard, gui, currentPlayer, theDice, gameSets, TextWindow);
                                    }
                                    if(currentSquare.getNumber() == 7 || currentSquare.getNumber() == 22 ||
                                            currentSquare.getNumber() == 36) // case of chance square
                                    {
                                        currentSquare.getChanceCard(players[currentPlayer], gameBoard, gui, currentPlayer, theDice, gameSets, TextWindow);
                                    }
                                    if(players[currentPlayer].passedGo()) // case that the special card moves player past Go
                                    {
                                        currentSquare = gameBoard.getSquare(0);
                                        players[currentPlayer].payPlayer(200);
                                    }
                                }
                                if(currentSquare.isTaxSquare()) // case that the current square is a tax square
                                {
                                    TextWindow.printMessage("You must pay $" + currentSquare.getRent());
                                    if(currentSquare.getRent() > players[currentPlayer].getBalance())
                                    { // player doesn't have money for tax
                                        players[currentPlayer].finePlayer(players[currentPlayer].getBalance());
                                        players[currentPlayer].eliminatePlayer();
                                    } else{ // player has money for tax
                                        players[currentPlayer].finePlayer(currentSquare.getRent());
                                        players[currentPlayer].displayStats(TextWindow);
                                    }
                                }
                                if(currentSquare.isCampoSquare()) // case that the current square is the Campo Square
                                {
                                    TextWindow.printMessage("You are just visiting Campo, so you won't be imprisoned. :)");
                                }
                                if(currentSquare.isGoToCampoSquare()) // case that the current square is "Go To Campo" Square
                                {
                                    TextWindow.printMessage("Go directly to Campo. Do not pass go, do not collect $200.");
                                    players[currentPlayer].imprisonPlayer();
                                    gui.step(gameBoard, players[currentPlayer], currentPlayer);
                                    theDice.resetDice();
                                }
                            }
                            theDice.resetDice();    // set total roll back to 0
                            goAgain = false;
                            doubles = gui.getDoubles();
                            if(doubles)      // case that the player rolled doubles
                                goAgain = true;
                        }
                    }

                    // Post Roll Options
                    postRollOptions(currentPlayer, players, theDice, TextWindow);
                    players[currentPlayer].displayStats(TextWindow);

                } else{     // case that the player is in Campo
                    TextWindow.printMessage("Player " + players[currentPlayer].getPlayerNum() +
                            ", you are in Campo, so you have 4 choices.");
                    TextWindow.printMessage("Option 1: Remain in Campo (you will only remain here for a max of 3 turns," +
                            " at which point you will have to pay $50).");
                    TextWindow.printMessage("Option 2: Pay $50 and get out of Campo now.");
                    TextWindow.printMessage("Option 3: Try to roll a doubles to get out of Campo for free.");
                    TextWindow.printMessage("Option 4: Play your 'Get out of Campo Free' card.");
                    boolean done = false;

                    while(!done) // while the player is still mulling over their options in Campo
                    {
                        int option = TextWindow.getChar();
                        while(option < 1 || option > 4)    // obtain player's choice
                        {
                            System.out.printf("");
                            option = TextWindow.getChar();
                        }

                        if(option == 1) // case that the player chooses to remain in Campo
                        {
                            TextWindow.printMessage("You have chosen to remain in Campo for this turn. " +
                                    "If you have spent 3 turns in Campo, however, you will automatically be freed.");
                            players[currentPlayer].incrementTurnsInJail();
                            done = true;
                        }
                        else if(option == 2) // case that player pays $50 to get out now
                        {
                            TextWindow.printMessage("You have chosen to pay $50 to get out of jail now.");
                            if(players[currentPlayer].getBalance() < 50) // case that they can't pay
                            {
                                TextWindow.printMessage("You cannot pay to get out of jail because you are broke!" +
                                        " Select another option.");
                            } else{ // case that they can pay
                                players[currentPlayer].finePlayer(50);
                                players[currentPlayer].freePlayer();
                                done = true;
                            }
                        }
                        else if(option == 3) // case that the player wants to try to roll a doubles to get out now for free
                        {
                            TextWindow.printMessage("You have chosen to try to get lucky. Good luck.");
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
                                TextWindow.printMessage("You got lucky. :)");
                                players[currentPlayer].freePlayer();
                            } else{ // case that the player doesn't roll doubles
                                TextWindow.printMessage("Better luck next time. :(");
                                players[currentPlayer].incrementTurnsInJail();
                            }
                            gui.resetRoll();
                            done = true;
                        } else{ // case that the player chooses to play a "Get out of Campo Free" card
                            TextWindow.printMessage("You have chosen to play your 'Get out of Campo Free' card.");
                            if(players[currentPlayer].hasGetOutOfCampoFree())
                            { // case that they have the card
                                TextWindow.printMessage("Very well, you will be freed.");
                                done = true;
                            }else{ // case that they do not have the card
                                TextWindow.printMessage("You do not have this card. Choose a different option.");
                            }
                            players[currentPlayer].getOutOfCampoFree();
                        }
                    }

                    if(players[currentPlayer].isFree()) // case that the player is now free
                    {
                        TextWindow.printMessage("YOU ARE NOW FREE FROM CAMPO!!!! You will be given all the options" +
                                " of a normal turn.");
                        currentPlayer--;
                        if(currentPlayer < 0)   // case that currentPlayer is decremented below player 1 (goes to
                            // player with highest number). The purpose of this is to make the game return the turn
                            // the current player after this loop ends
                            currentPlayer = players.length - 1;
                    } else{ // case that currentPlayer is not decremented below player 1
                        postRollOptions(currentPlayer, players, theDice, TextWindow);
                        players[currentPlayer].displayStats(TextWindow);
                    }
                }

                if(players[currentPlayer].isEliminated()) // case that the player was eliminated on this turn
                {
                    if(currentPlayer == 0) // case that currentPlayer is decremented below the first player
                        currentPlayer = players.length;
                    else // case that the current player isn't decremented below the first player
                        currentPlayer--;
                }

                if(players[currentPlayer].getBalance() >= 5000) // case that the player reaches a balance of 5000
                {
                    TextWindow.printMessage("Congratulations, Player " + (currentPlayer + 1) + ". YOU WIN!!!");
                    gameHasEnded = true;
                }
            } else{ // case that the current player is out (check to see how many players are still in)
                int notEliminated = 0;
                int pNumber = 0;
                while(pNumber < players.length) // get the number of players who aren't eliminated
                {
                    if(!players[pNumber].isEliminated())
                        notEliminated++;
                    pNumber++;
                }

                pNumber = 0;
                if(notEliminated == 1) // case that there is only one player left (the game is now over)
                {
                    while(players[pNumber].isEliminated())
                    {
                        pNumber++;
                    }
                    TextWindow.printMessage("Congratulations, Player " + (pNumber + 1) + ". YOU WIN!!!");
                    gameHasEnded = true;
                }
            }
            TextWindow.printMessage(" ");
            TextWindow.printMessage(" ");
            if(currentPlayer < players.length - 1) // it is now the next player's turn
                currentPlayer++;
            else // game has wrapped around to the first player again
                currentPlayer = 0;
        }
    }
}