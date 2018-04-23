
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame implements ActionListener {
    private JPanel Menu = new JPanel();
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;
    private JButton startButton = new JButton("Start");
    private JButton instructionsButton = new JButton("Instructions");
    private JButton exitButton = new JButton("Exit");



public MainMenu(){
    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    addButtons();
    add(Menu);
    setTitle("Main Menu");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.BLUE);
    this.setVisible(true);
}


private void addButtons(){
    startButton.addActionListener(this);
    instructionsButton.addActionListener(this);
    exitButton.addActionListener(this);

    Menu.add(startButton);
    Menu.add(instructionsButton);
    Menu.add(exitButton);
}

private void initUI(){
    JPanel GameBoard = new GameBoard();
    add(GameBoard);
    setTitle("Zagopoly");
    setSize(580, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
    @Override
    public void actionPerformed(ActionEvent e) {
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
