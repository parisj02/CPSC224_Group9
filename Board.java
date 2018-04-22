/**
 * This class creates an array of properties which acts as the board
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/15/2018
 */


public class Board
{
    //private Property[] SETS;
    private Property[] THE_BOARD;

    /**
     * constructor for class Board
     * @param players is the array containing all of the players in the game
     */
    public Board(Player players[])
    {
        //SETS = new Property[8];
        THE_BOARD = new Property[40];
        initAllSquares(players);
    }

    /**
     * getSquare returns a property in the board
     * @param squareNum is the position of the square on the board
     * @return a property in THE_BOARD
     */
    public Property getSquare(int squareNum)
    {
        return THE_BOARD[squareNum];
    }

    /**
     * initAllSquares initializes each square on the board
     * @param players is the array containing all players in the game
     */
    private void initAllSquares(Player players[])
    {
        THE_BOARD[0] = new GoSquare(players);
        THE_BOARD[1] = new PrimaryProperty(1, 60, 2, 50, "CM");
        THE_BOARD[2] = new SpecialCardSquare(2, "Community Service");
        THE_BOARD[3] = new PrimaryProperty(3, 60, 4, 50, "Madonna");
        THE_BOARD[4] = new TaxSquare(4, 200, "Tuition");
        THE_BOARD[5] = new RestaurantSquare(5, "Duffs Bistro");
        THE_BOARD[6] = new PrimaryProperty(6, 100, 6, 50, "Desmet");
        THE_BOARD[7] = new SpecialCardSquare(7, "Chance");
        THE_BOARD[8] = new PrimaryProperty(6, 100, 6, 50, "Chardan");
        THE_BOARD[9] = new PrimaryProperty(6, 120, 8, 50, "Welch");
        THE_BOARD[10] = new CampoSquare();
        THE_BOARD[11] = new PrimaryProperty(11, 140, 10, 100, "Twohy");
        THE_BOARD[12] = new UtilityProperty(12, "Plant Services");
        THE_BOARD[13] = new PrimaryProperty(13, 140, 10, 100, "Caughlin");
        THE_BOARD[14] = new PrimaryProperty(14, 160, 12, 100, "Dillon");
        THE_BOARD[15] = new RestaurantSquare(15, "1887");
        THE_BOARD[16] = new PrimaryProperty(16, 180, 14, 100, "Crosby");
        THE_BOARD[17] = new SpecialCardSquare(17, "Community Service");
        THE_BOARD[18] = new PrimaryProperty(18, 180, 14, 100, "Saint Aloysius");
        THE_BOARD[19] = new PrimaryProperty(19, 200, 16, 100, "Crosby");
        THE_BOARD[20] = new Property(20, 0, 0);
        THE_BOARD[21] = new PrimaryProperty(21, 220, 18, 150, "Hughes");
        THE_BOARD[22] = new SpecialCardSquare(22, "Chance");
        THE_BOARD[23] = new PrimaryProperty(23, 220, 18, 150, "Herak");
        THE_BOARD[24] = new PrimaryProperty(24, 240, 20, 150, "College Hall");
        THE_BOARD[25] = new RestaurantSquare(25, "Starbucks");
        THE_BOARD[26] = new PrimaryProperty(26, 260, 22, 150, "Paccar");
        THE_BOARD[27] = new PrimaryProperty(27, 260, 22, 150, "Jepson");
        THE_BOARD[28] = new UtilityProperty(28, "IT Services");
        THE_BOARD[29] = new PrimaryProperty(29, 280, 22, 150, "Tilford");
        THE_BOARD[30] = new GoToCampo();
        THE_BOARD[31] = new PrimaryProperty(31, 300, 26, 200, "Dussault");
        THE_BOARD[32] = new PrimaryProperty(32, 300, 26, 200, "Kennedy");
        THE_BOARD[33] = new SpecialCardSquare(33, "Community Service");
        THE_BOARD[34] = new PrimaryProperty(34, 320, 28, 200, "Hemmingson");
        THE_BOARD[35] = new RestaurantSquare(35, "The COG");
        THE_BOARD[36] = new SpecialCardSquare(36, "Chance");
        THE_BOARD[37] = new PrimaryProperty(37, 350, 35, 200, "McCarthey Athletic Center");
        THE_BOARD[38] = new TaxSquare(38, 75, "Mandatory Donation");
        THE_BOARD[39] = new PrimaryProperty(39, 400, 50, 200, "Volker Athletic Center");
    }
}
