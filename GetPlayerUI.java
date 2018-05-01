/**
 * This class creates a GUI that asks the users for the number of players
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/28/2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GetPlayerUI extends JFrame implements ActionListener
{
    private JPanel PlayerNumberMenu = new JPanel();
    private int DEFAULT_WIDTH = 400;
    private int DEFAULT_HEIGHT = 200;
    private JButton TwoPlayerButton = new JButton("2 Players");
    private JButton ThreePlayerButton = new JButton("3 Players");
    private JButton FourPlayerButton = new JButton("4 Players");
    private int NUM_PLAYERS;

    public GetPlayerUI()
    {
        NUM_PLAYERS = 0;
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        addButtons();
        add(PlayerNumberMenu);
        setTitle("Select Number of Players");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayerNumberMenu.setBackground(Color.BLUE);
        this.setVisible(true);
    }

    /**
     * addButtons adds the buttons to the main menu
     */
    private void addButtons()
    {
        TwoPlayerButton.addActionListener(this);
        ThreePlayerButton.addActionListener(this);
        FourPlayerButton.addActionListener(this);

        PlayerNumberMenu.add(TwoPlayerButton);
        PlayerNumberMenu.add(ThreePlayerButton);
        PlayerNumberMenu.add(FourPlayerButton);
    }

    /**
     * getNumPlayers returns the desired number of players
     * @return NUM_PLAYERS
     */
    public int getNumPlayers()
    {
        return NUM_PLAYERS;
    }

    /**
     * actionPerformed is a function inherited from the interface ActionListener that handles an event
     * @param e is the event in question
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == TwoPlayerButton)
        {
            NUM_PLAYERS = 2;
            this.setVisible(false);
        }
        if(source == ThreePlayerButton)
        {
            NUM_PLAYERS = 3;
            this.setVisible(false);
        }
        if (source == FourPlayerButton)
        {
            NUM_PLAYERS = 4;
            this.setVisible(false);
        }
    }

}
