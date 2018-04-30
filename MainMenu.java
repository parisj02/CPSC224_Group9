import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainMenu extends JFrame implements ActionListener
{
    private JPanel Menu = new JPanel();
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;
    private JButton startButton = new JButton("Start");
    private JButton instructionsButton = new JButton("Instructions");
    private JButton exitButton = new JButton("Exit");
    private GameBoardUI GUI;

    /**
     * constructor for class MainMenu
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
     * initGUI initializes the GUI of the game
     */
    public void initUI()
    {
        MouseCoordinateFinder mcf = new MouseCoordinateFinder();
        addMouseListener(mcf);

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
            System.exit(0);
        }
    }
}