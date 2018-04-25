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
     * displayPropertyInfo overrides displayPropertyInfo of class Property
     */
    public void displayPropertyInfo()
    {
        System.out.println("A " + this.NAME + " Square.");
    }

    /**
     * getServiceCard overrides getServiceCard of Property and gives the player a random card
     * @param currentPlayer is the player who landed on the square
     */
    public void getServiceCard(Player currentPlayer, Board gameBoard, GameBoardUI gui)
    {
        int CardNumber = (int) (Math.random() * 15 + 1);
        //int CardNumber = 12;

        switch (CardNumber) {
            case 1:
                CardNumber = 1;
                System.out.println("Buy girl scout cookies in hemmingson, pay 15");
                currentPlayer.finePlayer(15);
                break;

            case 2:
                CardNumber = 2;
                System.out.println("Buy your tickets for the spring formal, pay 50");
                currentPlayer.finePlayer(50);
                break;

            case 3:
                CardNumber = 3;
                System.out.println("Sell some extra clothes on ebay, earn 100");
                currentPlayer.payPlayer(100);
                break;

            case 4:
                CardNumber = 4;
                System.out.println("Advance straight to Go!");
                currentPlayer.relocatePlayer(0);
                break;

            case 5:
                CardNumber = 5;
                System.out.println("Get caught speeding, pay 100");
                currentPlayer.finePlayer(100);
                break;

            case 6:
                CardNumber = 6;
                System.out.println("Get written up for a noise violation, go straight to Campus Security");
                currentPlayer.imprisonPlayer();
                break;

            case 7:
                CardNumber = 7;
                System.out.println("Find a 50 dollar bill on the ground outside of your dorm, earn 50");
                currentPlayer.payPlayer(50);
                break;

            case 8:
                CardNumber = 8;
                System.out.println("Go out to a sushi dinner date, pay 60");
                currentPlayer.finePlayer(60);
                break;

            case 9:
                CardNumber = 9;
                System.out.println("Hamburgers at Late Night, go straight to Hemmingson");
                currentPlayer.relocatePlayer(34);
                gui.step(gameBoard, currentPlayer);

                break;

            case 10:
                CardNumber = 10;
                System.out.println("Friend invited you over to their apartment, go straight to Dussault");
                currentPlayer.relocatePlayer(31);
                gui.step(gameBoard, currentPlayer);
                break;

            case 11:
                CardNumber = 11;
                System.out.println("Lose your room key, pay 50");
                currentPlayer.finePlayer(50);
                break;

            case 12:
                CardNumber = 12;
                System.out.println("Property Taxes are due, Pay 50 for each house owned and 75 for each hotel");
                int houseCost = (currentPlayer.getNumberOfHouses() * 50);
                int hotelCost = (currentPlayer.getNumberOfHotels() * 75);
                currentPlayer.finePlayer(houseCost + hotelCost);
                break;

            case 13:
                CardNumber = 13;
                System.out.println("Family member sends you money for getting good grades, earn 25");
                currentPlayer.payPlayer(25);
                break;

            case 14:
                CardNumber = 14;
                System.out.println("Donate money to a local animal shelter because they had cute puppies, pay 50");
                currentPlayer.finePlayer(50);
                break;

            case 15:
                CardNumber = 15;
                System.out.println("Go on a tour of the new athletic building, go straight to Volker");
                currentPlayer.relocatePlayer(39);
                gui.step(gameBoard, currentPlayer);
                break;
        }
    }

    /**
     * getChanceCard retrieves a chance card for the player
     * @param currentPlayer is the player who landed on the square
     */
    public void getChanceCard(Player currentPlayer, Board gameBoard, GameBoardUI gui) {
        int CardNumber = (int)(Math.random() * 15 + 1);

        switch(CardNumber) {
            case 1: CardNumber = 1;
                System.out.println("Advance Straight to Go!");
                currentPlayer.relocatePlayer(0);
                gui.step(gameBoard, currentPlayer);
                break;

            case 2: CardNumber = 2;
                System.out.println("Pulling an all nighter, take a trip to Starbucks");
                currentPlayer.relocatePlayer(25);
                gui.step(gameBoard, currentPlayer);
                break;

            case 3: CardNumber = 3;
                System.out.println("Time for a Zags Game, Advance to McCarthy");
                currentPlayer.relocatePlayer(39);
                gui.step(gameBoard, currentPlayer);
                break;

            case 4: CardNumber = 4;
                System.out.println("Room searched by RA's, Go straight to Campus Security");
                currentPlayer.relocatePlayer(10);
                gui.step(gameBoard, currentPlayer);
                break;

            case 5: CardNumber = 5;
                System.out.println("Parking Violation, pay 50");
                currentPlayer.finePlayer(50);
                gui.step(gameBoard, currentPlayer);
                break;

            case 6: CardNumber = 6;
                System.out.println("Win the biggest Zags Fan Competition, earn 75");
                currentPlayer.payPlayer(75);
                gui.step(gameBoard, currentPlayer);
                break;

            case 7: CardNumber = 7;
                System.out.println("Time for Biology, Go straight to Hughes");
                currentPlayer.relocatePlayer(21);
                gui.step(gameBoard, currentPlayer);
                break;

            case 8: CardNumber = 8;
                System.out.println("Property Improvement Time, Pay 25 for each house owned and 40 for each hotel");
                int houseCost = (currentPlayer.getNumberOfHouses() * 25);
                int hotelCost = (currentPlayer.getNumberOfHotels() * 40);
                currentPlayer.finePlayer(houseCost + hotelCost);
                break;

            case 9: CardNumber = 9;
                System.out.println("Zags are in the final four, pay 30 to get some new gear");
                currentPlayer.finePlayer(30);
                break;

            case 10: CardNumber = 10;
                System.out.println("Win a kareoke competition at late night, earn 25");
                currentPlayer.payPlayer(25);
                break;

            case 11: CardNumber = 11;
                System.out.println("Time for philosophy, go straight to college hall");
                currentPlayer.relocatePlayer(24);
                gui.step(gameBoard, currentPlayer);
                break;

            case 12: CardNumber = 12;
                System.out.println("Have to buy an online homework code, play 60");
                currentPlayer.payPlayer(60);
                break;

            case 13: CardNumber = 13;
                System.out.println("Found a nice parking spot, go to free parking");
                currentPlayer.relocatePlayer(20);
                gui.step(gameBoard, currentPlayer);
                break;

            case 14: CardNumber = 14;
                System.out.println("Get some pre basketball game 'supplies' for friends, pay 25");
                currentPlayer.finePlayer(25);
                break;

            case 15: CardNumber = 15;
                System.out.println("You lost your zag card, go straight to hemmingson to check lost and found");
                currentPlayer.relocatePlayer(34);
                gui.step(gameBoard, currentPlayer);
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
}
