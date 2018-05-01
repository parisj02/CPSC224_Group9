/**
 * This class is a child class of class Property that creates a special card square
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/15/2018
 */

public class SpecialCardSquare extends Property
{
    //private SpecialCards SPECIALS;
    //private CommunityService COMMUNITY_CARDS;
    //private Chance CHANCE_CARDS;

    /**
     * constructor for class SpecialCardSquare
     * @param squareNum is the position of the square on the board
     */
    public SpecialCardSquare(int squareNum, String name, int XCoord, int YCoord)
    {
        super(squareNum, 0, 0, XCoord, YCoord);
        this.NAME = name;
    }

    /**
     * getServiceCard gives the current player a service card
     * @param currentPlayer is the player who landed on the square
     * @param gameBoard is the game board
     * @param gui is the user interface that displays the board
     * @param currentPiece is the piece of the current player
     * @param theDice is the game dice
     * @param gameSets are the sets in the game
     */
    public void getServiceCard(Player currentPlayer, Board gameBoard, GameBoardUI gui, int currentPiece, ZagopolyDice theDice, The_Sets gameSets, ZagopolyTextWindow TextWindow)
    {
        int CardNumber = (int) (Math.random() * 15 + 1);
        //int CardNumber = 10;

        switch (CardNumber) {
            case 1:
                //CardNumber = 1;
                TextWindow.printMessage("Buy girl scout cookies in Hemmingson, pay $15.");
                if(currentPlayer.getBalance() < 15)
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(15);
                }
                break;

            case 2:
                //CardNumber = 2;
                TextWindow.printMessage("Buy your tickets for the spring formal, pay $50.");
                if(currentPlayer.getBalance() < 50)
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(50);
                }
                break;

            case 3:
                //CardNumber = 3;
                TextWindow.printMessage("Sell some extra clothes on ebay, earn $100.");
                currentPlayer.payPlayer(100);
                break;

            case 4:
                //CardNumber = 4;
                TextWindow.printMessage("Advance straight to Go!");
                currentPlayer.relocatePlayer(0);
                break;

            case 5:
                //CardNumber = 5;
                TextWindow.printMessage("Get caught speeding, pay $100.");
                if(currentPlayer.getBalance() < 100)
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(100);
                }
                break;

            case 6:
                //CardNumber = 6;
                TextWindow.printMessage("Get written up for a noise violation, go straight to Campus Security.");
                currentPlayer.imprisonPlayer();
                break;

            case 7:
                //CardNumber = 7;
                TextWindow.printMessage("Find a $50 dollar bill on the ground outside of your dorm, earn $50.");
                currentPlayer.payPlayer(50);
                break;

            case 8:
                //CardNumber = 8;
                TextWindow.printMessage("Go out to a sushi dinner date, pay $60.");
                if(currentPlayer.getBalance() < 60)
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(60);
                }
                break;

            case 9:
                //CardNumber = 9;
                TextWindow.printMessage("Hamburgers at Late Night, go straight to Hemmingson.");
                currentPlayer.relocatePlayer(34);
                gui.step(gameBoard, currentPlayer, currentPiece);
                if(gameBoard.getSquare(34).isOwned())
                {
                    payRelocationRent(gameBoard, currentPlayer, 34, TextWindow);
                } else{
                    gameBoard.getSquare(34).addOwner(currentPlayer, theDice, gameSets, TextWindow);
                }
                break;

            case 10:
                //CardNumber = 10;
                TextWindow.printMessage("Friend invited you over to their apartment, go straight to Dussault.");
                currentPlayer.relocatePlayer(31);
                gui.step(gameBoard, currentPlayer, currentPiece);
                if(gameBoard.getSquare(31).isOwned())
                {
                    payRelocationRent(gameBoard, currentPlayer, 31, TextWindow);
                } else{
                    gameBoard.getSquare(31).addOwner(currentPlayer, theDice, gameSets, TextWindow);
                }
                break;

            case 11:
                //CardNumber = 11;
                TextWindow.printMessage("Lose your room key, pay $50.");
                if(currentPlayer.getBalance() < 50)
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(50);
                }
                break;

            case 12:
                //CardNumber = 12;
                TextWindow.printMessage("Property Taxes are due, Pay $50 for each house owned and $75 for each hotel");
                int houseCost = (currentPlayer.getNumberOfHouses() * 50);
                int hotelCost = (currentPlayer.getNumberOfHotels() * 75);
                if(currentPlayer.getBalance() < (hotelCost + houseCost))
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(hotelCost + houseCost);
                }
                break;

            case 13:
                //CardNumber = 13;
                TextWindow.printMessage("Family member sends you money for getting good grades, earn $25");
                currentPlayer.payPlayer(25);
                break;

            case 14:
                //CardNumber = 14;
                TextWindow.printMessage("'Get out of Campo' Free card!!! If you play this card on any given turn while " +
                        "in Campo, you will automatically be freed. This card will be returned to the deck when you " +
                        "play it.");
                currentPlayer.giveGetOutOfCampoFreeCard();
                break;

            case 15:
                //CardNumber = 15;
                TextWindow.printMessage("Go on a tour of the new athletic building, go straight to Volkar.");
                currentPlayer.relocatePlayer(39);
                gui.step(gameBoard, currentPlayer, currentPiece);
                if(gameBoard.getSquare(39).isOwned())
                {
                    payRelocationRent(gameBoard, currentPlayer, 39, TextWindow);
                } else{
                    gameBoard.getSquare(39).addOwner(currentPlayer, theDice, gameSets, TextWindow);
                }
                break;
        }
    }

    /**
     * getChanceCard gives the current player a chance
     * @param currentPlayer is the player who landed on the square
     * @param gameBoard is the game board
     * @param gui is the user interface that displays the board
     * @param currentPiece is the piece of the current player
     * @param theDice is the game dice
     * @param gameSets are the sets in the game
     */
    public void getChanceCard(Player currentPlayer, Board gameBoard, GameBoardUI gui, int currentPiece, ZagopolyDice theDice, The_Sets gameSets, ZagopolyTextWindow TextWindow)
    {
        int CardNumber = (int)(Math.random() * 15 + 1);
        //int CardNumber = 12;

        switch(CardNumber) {
            case 1: //CardNumber = 1;
                TextWindow.printMessage("Advance Straight to Go!");
                currentPlayer.relocatePlayer(0);
                gui.step(gameBoard, currentPlayer, currentPiece);
                break;

            case 2: //CardNumber = 2;
                TextWindow.printMessage("Pulling an all nighter, take a trip to Starbucks.");
                currentPlayer.relocatePlayer(25);
                gui.step(gameBoard, currentPlayer, currentPiece);
                if(gameBoard.getSquare(25).isOwned())
                {
                    payRelocationRent(gameBoard, currentPlayer, 25, TextWindow);
                } else{
                    gameBoard.getSquare(25).addOwner(currentPlayer, theDice, gameSets, TextWindow);
                }
                break;

            case 3: //CardNumber = 3;
                TextWindow.printMessage("Time for a Zags Game, Advance to McCarthy.");
                currentPlayer.relocatePlayer(37);
                gui.step(gameBoard, currentPlayer, currentPiece);
                if(gameBoard.getSquare(37).isOwned())
                {
                    payRelocationRent(gameBoard, currentPlayer, 37, TextWindow);
                } else{
                    gameBoard.getSquare(37).addOwner(currentPlayer, theDice, gameSets, TextWindow);
                }
                break;

            case 4: //CardNumber = 4;
                TextWindow.printMessage("Room searched by RA's, Go straight to Campus Security.");
                currentPlayer.imprisonPlayer();
                break;

            case 5: //CardNumber = 5;
                TextWindow.printMessage("Parking Violation, pay $50.");
                if(currentPlayer.getBalance() < 50)
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(50);
                }
                break;

            case 6: //CardNumber = 6;
                TextWindow.printMessage("Win the biggest Zags Fan Competition, earn $75.");
                currentPlayer.payPlayer(75);
                break;

            case 7: //CardNumber = 7;
                TextWindow.printMessage("Time for Biology, Go straight to Hughes.");
                currentPlayer.relocatePlayer(21);
                gui.step(gameBoard, currentPlayer, currentPiece);
                if(gameBoard.getSquare(21).isOwned())
                {
                    payRelocationRent(gameBoard, currentPlayer, 21, TextWindow);
                } else{
                    gameBoard.getSquare(21).addOwner(currentPlayer, theDice, gameSets, TextWindow);
                }
                break;

            case 8: //CardNumber = 8;
                TextWindow.printMessage("Property Improvement Time, Pay $25 for each house owned and $40 for each hotel.");
                int houseCost = (currentPlayer.getNumberOfHouses() * 25);
                int hotelCost = (currentPlayer.getNumberOfHotels() * 40);
                if(currentPlayer.getBalance() < (houseCost + hotelCost))
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(houseCost + hotelCost);
                }
                break;

            case 9: //CardNumber = 9;
                TextWindow.printMessage("Zags are in the final four, pay $30 to get some new gear.");
                if(currentPlayer.getBalance() < 30)
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(30);
                }
                break;

            case 10: //CardNumber = 10;
                TextWindow.printMessage("Win a kareoke competition at late night, earn $25.");
                currentPlayer.payPlayer(25);
                break;

            case 11: //CardNumber = 11;
                TextWindow.printMessage("Time for philosophy, go straight to College Hall.");
                currentPlayer.relocatePlayer(24);
                gui.step(gameBoard, currentPlayer, currentPiece);
                if(gameBoard.getSquare(24).isOwned())
                {
                    payRelocationRent(gameBoard, currentPlayer, 24, TextWindow);
                } else{
                    gameBoard.getSquare(24).addOwner(currentPlayer, theDice, gameSets, TextWindow);
                }
                break;

            case 12: //CardNumber = 12;
                TextWindow.printMessage("'Get out of Campo' Free card!!! If you play this card on any given turn while " +
                        "in Campo, you will automatically be freed. This card will be returned to the deck when you " +
                        "play it.");
                currentPlayer.giveGetOutOfCampoFreeCard();
                break;

            case 13: //CardNumber = 13;
                TextWindow.printMessage("Found a nice parking spot, go to Free Parking.");
                currentPlayer.relocatePlayer(20);
                gui.step(gameBoard, currentPlayer, currentPiece);
                break;

            case 14: //CardNumber = 14;
                TextWindow.printMessage("Get some pre basketball game 'supplies' for friends, pay $25.");
                if(currentPlayer.getBalance() < 25)
                {
                    currentPlayer.finePlayer(currentPlayer.getBalance());
                    currentPlayer.eliminatePlayer();
                } else{
                    currentPlayer.finePlayer(25);
                }
                break;

            case 15: //CardNumber = 15;
                TextWindow.printMessage("You lost your zag card, go straight to Hemmingson to check lost and found.");
                currentPlayer.relocatePlayer(34);
                gui.step(gameBoard, currentPlayer, currentPiece);
                if(gameBoard.getSquare(24).isOwned())
                {
                    payRelocationRent(gameBoard, currentPlayer, 24, TextWindow);
                } else{
                    gameBoard.getSquare(24).addOwner(currentPlayer, theDice, gameSets, TextWindow);
                }
                break;
        }
    }

    /**
     * isCardSquare determines if the current square is a card square
     * @return true
     */
    public boolean isCardSquare()
    {
        return true;
    }

    /**
     * payRelocationRent has the current player pay rent at the square he is moved to
     * @param gameBoard is the game board
     * @param currentPlayer is the current player
     * @param squareNum is the number of the square that the player is moved to
     */
    private void payRelocationRent(Board gameBoard, Player currentPlayer, int squareNum, ZagopolyTextWindow TextWindow)
    {
        if (currentPlayer.getBalance() < gameBoard.getSquare(squareNum).getRent())
        {   // case that the player cannot pay the rent due to insufficient funds
            TextWindow.printMessage("You do not have enough money in your account to pay " +
                    "the owner of this property the desired rent.");
            gameBoard.getSquare(squareNum).payOwnerRent();
            currentPlayer.finePlayer(currentPlayer.getBalance());
            // drains the current player's account
            currentPlayer.eliminatePlayer();
        } else {  // case that the player can pay the rent
            gameBoard.getSquare(squareNum).payOwnerRent();
            currentPlayer.finePlayer(gameBoard.getSquare(squareNum).getRent());
            TextWindow.printMessage("You have paid Player " + gameBoard.getSquare(squareNum).getOwnerNum() +
                    " $" + gameBoard.getSquare(squareNum).getRent());
        }
    }

}