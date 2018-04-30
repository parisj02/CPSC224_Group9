/**
 * This class creates the game board GUI
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardUI extends JPanel implements ActionListener
{
    private int ROLL;
    private boolean DOUBLES;
    private JPanel buttonPanel = new JPanel();
    private PlayerIcon[] gamePieces;
    //private int numberOfPlayers;
    private ZagopolyDice DICE;
    private JButton DiceButton = new JButton("Roll");

    /**
     * constructor for class GameBoardUI
     * @param iconArray is the array of player icons
     * @param numberOfPlayers is the number of players in the game
     */
    public GameBoardUI(PlayerIcon[] iconArray, int numberOfPlayers)
    {
        ROLL = 0;
        DOUBLES = false;
        gamePieces = new PlayerIcon[numberOfPlayers];
        for(int i = 0; i < iconArray.length; i++)
        {
            gamePieces[i] = iconArray[i];
        }
        DICE = new ZagopolyDice(2);
        addButton();
    }


    public void doDrawing(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < gamePieces.length; i++)
        {
            g2d.drawImage(gamePieces[i].getImage(i), gamePieces[i].getX(), gamePieces[i].getY(), this);
        }
    }

    public void step(Board gameBoard, Player currentPlayer, int currentPiece)
    {
        gamePieces[currentPiece].move(gameBoard, currentPlayer);
        repaint(getX(),getY(),getWidth(),getHeight());
    }

    public void paintComponent(Graphics g)
    {
        try {
            BufferedImage Image = ImageIO.read(new File("res/board.png"));
            super.paintComponent(g);
            g.drawImage(Image,0,0,null);
            doDrawing(g);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * addButton adds the buttons to the game board gui
     */
    private void addButton()
    {
        buttonPanel.setSize(20, 20);
        this.DiceButton.setLocation(283, 306);
        this.DiceButton.setPreferredSize(new Dimension(50, 50));
        this.buttonPanel.setBackground(Color.RED);
        this.buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        this.buttonPanel.add(Box.createVerticalGlue());
        this.buttonPanel.add(DiceButton);
        this.buttonPanel.add(Box.createVerticalGlue());
        DiceButton.addActionListener(this);
        this.add(buttonPanel);
        buttonPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == DiceButton)
        {
            DICE.rollDice();
            ROLL = DICE.getTotalRoll();
            DOUBLES = DICE.doubles();
            DICE.displayRoll();
            DICE.resetDice();
            buttonPanel.setVisible(false);
        }
    }

    /**
     * revealDice reveals the "roll" button by making the buttonPanel visible
     */
    public void revealDice()
    {
        buttonPanel.setVisible(true);
    }

    /**
     * resetRoll resets the roll to 0 and DOUBLES to false
     */
    public void resetRoll()
    {
        ROLL = 0;
        DOUBLES = false;
    }

    /**
     * getRoll returns the value of the roll
     * @return Dice.getTotalRoll()
     */
    public int getRoll()
    {
        revealDice();
        return ROLL;
    }

    public boolean getDoubles()
    {
        return DOUBLES;
    }
}
