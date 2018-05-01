/**
 * This class creates the player icon
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/18/2018
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerIcon
{

    private int startCount;
    private int x;
    private int y;
    private BufferedImage image1 = null;
    private BufferedImage image2 = null;
    private BufferedImage image3 = null;
    private BufferedImage image4 = null;


        public PlayerIcon(int count)
        {
            startCount = count;
            initImages();
            x = getStartX();
            y = getStartY();

        }

    /**
     * getImage returns the image of the player icon
     *
     * @return image
     */
    public Image getImage(int imageCount) {
        if (imageCount == 0) {
            return image1;
        } else if (imageCount == 1) {
            return image2;
        } else if (imageCount == 2) {
            return image3;
        } else {
            return image4;
        }
    }


    private int getStartX(){
        if (startCount == 0){
            return 608;
        }
        else if(startCount == 1){
            return 633;
        }
        else if(startCount == 2){
            return 608;
        }
        else{
            return 633;
        }
    }

    private int getStartY(){
        if (startCount == 0){
            return 610;
        }
        else if(startCount == 1){
            return 610;
        }
        else if(startCount == 2){
            return 640;
        }
        else{
            return 640;
        }
    }

    /**
     * loadImage loads the player icon image to the screen
     */
    private void initImages() {
        try {
            image1 = ImageIO.read(new File("res/playerIcon1.png"));
            image2 = ImageIO.read(new File("res/playerIcon2t.png"));
            image3 = ImageIO.read(new File("res/playerIcon3t.png"));
            image4 = ImageIO.read(new File("res/playerIcon4t.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * move moves the player icon on the board
     *
     * @param theBoard      is the game board
     * @param currentPlayer is the player in question
     */
    public void move(Board theBoard, Player currentPlayer) {
        Property tempProperty = theBoard.getSquare(currentPlayer.currentSquare());
        int moveToX = tempProperty.getX();
        int moveToY = tempProperty.getY();
        x = moveToX;
        y = moveToY;
    }

    /**
     * getX gets the x coordinate of the player icon
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * getY returns the y coordinate of the player icon
     *
     * @return y
     */
    public int getY() {
        return y;
    }
}

