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
        THE_BOARD[0] = new GoSquare(players, 508, 550);
        THE_BOARD[1] = new PrimaryProperty(1, 60, 2, 50, "CM", 449, 520);
        THE_BOARD[2] = new SpecialCardSquare(2, "Community Service", 405, 550);
        THE_BOARD[3] = new PrimaryProperty(3, 60, 4, 50, "Madonna", 365, 520);
        THE_BOARD[4] = new TaxSquare(4, 200, "Tuition", 324, 520);
        THE_BOARD[5] = new RestaurantSquare(5, "Duffs Bistro", 268, 520);
        THE_BOARD[6] = new PrimaryProperty(6, 100, 6, 50, "Desmet", 223, 520);
        THE_BOARD[7] = new SpecialCardSquare(7, "Chance", 175, 520);
        THE_BOARD[8] = new PrimaryProperty(6, 100, 6, 50, "Chardan", 130, 520);
        THE_BOARD[9] = new PrimaryProperty(6, 120, 8, 50, "Welch", 86, 520);
        THE_BOARD[10] = new CampoSquare(3, 530);
        THE_BOARD[11] = new PrimaryProperty(11, 140, 10, 100, "Twohy", 30, 455);
        THE_BOARD[12] = new UtilityProperty(12, "Plant Services", 30, 408);
        THE_BOARD[13] = new PrimaryProperty(13, 140, 10, 100, "Caughlin", 30, 366);
        THE_BOARD[14] = new PrimaryProperty(14, 160, 12, 100, "Dillon", 30, 319);
        THE_BOARD[15] = new RestaurantSquare(15, "1887", 30, 272);
        THE_BOARD[16] = new PrimaryProperty(16, 180, 14, 100, "Crosby", 30, 226);
        THE_BOARD[17] = new SpecialCardSquare(17, "Community Service", 30, 182);
        THE_BOARD[18] = new PrimaryProperty(18, 180, 14, 100, "Saint Aloysius", 30, 134);
        THE_BOARD[19] = new PrimaryProperty(19, 200, 16, 100, "Crosby", 30, 90);
        THE_BOARD[20] = new Property(20, 0, 0, 30, 30);
        THE_BOARD[21] = new PrimaryProperty(21, 220, 18, 150, "Hughes", 85, 33);
        THE_BOARD[22] = new SpecialCardSquare(22, "Chance", 130, 33);
        THE_BOARD[23] = new PrimaryProperty(23, 220, 18, 150, "Herak", 177, 33);
        THE_BOARD[24] = new PrimaryProperty(24, 240, 20, 150, "College Hall", 224, 33);
        THE_BOARD[25] = new RestaurantSquare(25, "Starbucks", 269, 33);
        THE_BOARD[26] = new PrimaryProperty(26, 260, 22, 150, "Paccar", 315, 33);
        THE_BOARD[27] = new PrimaryProperty(27, 260, 22, 150, "Jepson", 361, 33);
        THE_BOARD[28] = new UtilityProperty(28, "IT Services", 407, 33);
        THE_BOARD[29] = new PrimaryProperty(29, 280, 22, 150, "Tilford", 452, 33);
        THE_BOARD[30] = new GoToCampo(510, 33);
        THE_BOARD[31] = new PrimaryProperty(31, 300, 26, 200, "Dussault", 508, 185);
        THE_BOARD[32] = new PrimaryProperty(32, 300, 26, 200, "Kennedy", 508, 133);
        THE_BOARD[33] = new SpecialCardSquare(33, "Community Service", 508, 180);
        THE_BOARD[34] = new PrimaryProperty(34, 320, 28, 200, "Hemmingson", 508, 226);
        THE_BOARD[35] = new RestaurantSquare(35, "The COG", 508, 270);
        THE_BOARD[36] = new SpecialCardSquare(36, "Chance", 508, 317);
        THE_BOARD[37] = new PrimaryProperty(37, 350, 35, 200, "McCarthey Athletic Center", 508, 360);
        THE_BOARD[38] = new TaxSquare(38, 75, "Mandatory Donation", 508, 409);
        THE_BOARD[39] = new PrimaryProperty(39, 400, 50, 200, "Volker Athletic Center", 508, 454);
    }
}
