/**
 * This class extends class Property in order to create a primary, or "color" property
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/11/2018
 */

public class PrimaryProperty extends Property
{
    private int HOUSE_PRICE;
    private int NUM_HOUSES;
    private int NUM_HOTELS;
    private enum Color {Purple, LightBlue, Pink, Orange, Red, Yellow, Green, DarkBlue}
    //private Color SET_COLOR;

    /**
     * constructor for class PrimaryProperty
     * @param propertyNum is primary property's position on the board
     * @param propertyPrice is the price of purchasing the primary property
     * @param propertyRent is the rent that must be paid on that primary property to the property's owner
     *                     by other players who land there
     * @param name is the name of the primary property (e.g. Kennedy Apartments)
     */
    public PrimaryProperty(int propertyNum, int propertyPrice, int propertyRent, int priceOfAHouse, String name)
    {
        super(propertyNum, propertyPrice, propertyRent);
        this.HOUSE_PRICE = priceOfAHouse;
        this.NUM_HOUSES = 0;
        this.NUM_HOTELS = 0;
        this.IS_FOR_SALE = true;
        this.NAME = name;
        //assignColor(propertyNum);
    }

    // Needed functions:
        // Adding a house
        // Adding a hotel
    /**
     * getColor returns the color of the primary property
     * @return a String version of the SET_COLOR
     */
    /*public String getColor()
    {
        return SET_COLOR.toString();
    }*/

    /**
     * assignColor assigns a certain color to the property
     * @param propertyNum is the property's position on the board
     */
    /*private void assignColor(int propertyNum)
    {
        if(propertyNum == 1 || propertyNum == 3)
        {
            SET_COLOR = Color.Purple;
        }
        if(propertyNum == 6 || propertyNum == 8 || propertyNum == 9)
        {
            SET_COLOR = Color.LightBlue;
        }
        if(propertyNum == 11 || propertyNum == 13 || propertyNum == 14)
        {
            SET_COLOR = Color.Pink;
        }
        if(propertyNum == 16 || propertyNum == 18 || propertyNum == 19)
        {
            SET_COLOR = Color.Orange;
        }
        if(propertyNum == 21 || propertyNum == 23 || propertyNum == 24)
        {
            SET_COLOR = Color.Red;
        }
        if(propertyNum == 26 || propertyNum == 28 || propertyNum == 29)
        {
            SET_COLOR = Color.Yellow;
        }
        if(propertyNum == 31 || propertyNum == 32 || propertyNum == 34)
        {
            SET_COLOR = Color.Green;
        }
        if(propertyNum == 37 || propertyNum == 39)
        {
            SET_COLOR = Color.DarkBlue;
        }
    }*/
}
