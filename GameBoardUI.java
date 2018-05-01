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
    private JPanel DiceButtonPanel = new JPanel();
    private PlayerIcon[] gamePieces;
    private ZagopolyTextWindow TEXT_WINDOW;
    private ZagopolyDice DICE;
    private JButton DiceButton = new JButton("Roll");

    /**
     * constructor for class GameBoardUI
     * @param iconArray is the array of player icons
     * @param numberOfPlayers is the number of players in the game
     */
    public GameBoardUI(PlayerIcon[] iconArray, int numberOfPlayers, ZagopolyTextWindow TextWindow)
    {
        ROLL = 0;
        DOUBLES = false;
        gamePieces = new PlayerIcon[numberOfPlayers];
        for(int i = 0; i < iconArray.length; i++)
        {
            gamePieces[i] = iconArray[i];
        }
        DICE = new ZagopolyDice(2);
        addDiceButton();
        TEXT_WINDOW = TextWindow;
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
    private void addDiceButton()
    {
        DiceButtonPanel.setSize(20, 20);
        this.DiceButton.setLocation(283, 306);
        this.DiceButton.setPreferredSize(new Dimension(50, 50));
        this.DiceButtonPanel.setBackground(Color.RED);
        this.DiceButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.DiceButtonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.DiceButtonPanel.setLayout(new BoxLayout(DiceButtonPanel, BoxLayout.PAGE_AXIS));
        this.DiceButtonPanel.add(Box.createVerticalGlue());
        this.DiceButtonPanel.add(DiceButton);
        this.DiceButtonPanel.add(Box.createVerticalGlue());
        DiceButton.addActionListener(this);
        this.add(DiceButtonPanel);
        DiceButtonPanel.setVisible(false);
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
            DICE.displayRoll(TEXT_WINDOW);
            DICE.resetDice();
            DiceButtonPanel.setVisible(false);
        }
    }

    /**
     * revealDice reveals the "roll" button by making the DiceButtonPanel visible
     */
    public void revealDice()
    {
        DiceButtonPanel.setVisible(true);
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
