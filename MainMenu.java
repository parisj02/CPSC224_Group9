/**
 * This class creates the main menu of the game of Zag-opoly
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/18/2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class MainMenu extends JFrame implements ActionListener
{
    private JPanel Menu = new JPanel();
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 200;
    private JButton startButton = new JButton("Start");
    private JButton instructionsButton = new JButton("Instructions");
    private JButton exitButton = new JButton("Exit");
    private GameBoardUI GUI;

    /**
     * constructor for class MainMenu
     * @param gui is the object containing
     */
    public MainMenu(GameBoardUI gui)
    {
        GUI = gui;
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        addButtons();
        add(Menu);
        setTitle("Main Menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLUE);
        this.setVisible(true);
        Menu.setBackground(Color.RED);
    }

    /**
     * addButtons adds the buttons to the main menu
     */
    private void addButtons()
    {
        startButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        exitButton.addActionListener(this);

        Menu.add(startButton);
        Menu.add(instructionsButton);
        Menu.add(exitButton);
    }

    /**
     * initGUI initializes the GUI of the game board
     */
    public void initUI()
    {
        add(GUI);
        setTitle("Zagopoly");
        setSize(697, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * actionPerformed is a function inherited from the interface ActionListener that handles an event
     * @param e is the event in question
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == exitButton){
            System.exit(0);
        }
        else if(source == startButton){
            Menu.setVisible(false);
            initUI();
        }
        else if (source == instructionsButton){
            try {
                openInstructions();
            }
            catch (IOException E){
                E.printStackTrace();
            }
        }
    }

    /**
     * openInstructions opens the Instructions text file
     * @throws IOException
     */
    public void openInstructions() throws IOException
    {
        //text file, should be opening in default text editor
        File file = new File("res/Instructions.txt");
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
    }
}