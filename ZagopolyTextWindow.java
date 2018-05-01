
import java.awt.*;
import java.awt.event.*;
import java.io.InputStreamReader;
import javax.swing.*;

public class ZagopolyTextWindow extends JFrame implements ActionListener
{
    private JPanel TextWindow = new JPanel();
    private JTextField TEXT_FIELD;
    private JTextArea TEXT_AREA;
    private String USER_TEXT = "0";
    private final static String newLine = "\n";

    /**
     * constructor for class ZagopolyTextWindow
     */
    public ZagopolyTextWindow()
    {
        setSize(600, 600);
        add(TextWindow);
        setTitle("Game Progress");

        TEXT_FIELD = new JTextField(20);
        TEXT_FIELD.addActionListener(this);
        TEXT_FIELD.setEditable(true);

        TEXT_AREA = new JTextArea(30, 50);
        TEXT_AREA.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(TEXT_AREA);

        // Add components to this panel
        GridBagConstraints Constraints = new GridBagConstraints();
        Constraints.gridwidth = GridBagConstraints.REMAINDER;

        Constraints.fill = GridBagConstraints.HORIZONTAL;
        TextWindow.add(TEXT_FIELD, Constraints);

        Constraints.fill = GridBagConstraints.BOTH;
        Constraints.weightx = 1.0;
        Constraints.weighty = 1.0;
        TextWindow.add(scrollPane, Constraints);
        this.setVisible(true);
    }

    /**
     * actionPerformed implements the function from ActionListener
     * @param e is the event in question
     */
    public void actionPerformed(ActionEvent e)
    {
        String text = TEXT_FIELD.getText();
        TEXT_AREA.append(text + newLine);
        TEXT_FIELD.selectAll();
        USER_TEXT = text;

        // Make sure new text is visible, even if there
        // was a selection in the text area
        TEXT_AREA.setCaretPosition(TEXT_AREA.getDocument().getLength());
    }

    /**
     * printMessage prints a message to the text area
     * @param message is the message to be printed
     */
    public void printMessage(String message)
    {
        TEXT_AREA.append(message + newLine);
        TEXT_FIELD.selectAll();
        TEXT_AREA.setCaretPosition(TEXT_AREA.getDocument().getLength());
    }

    /**
     * resetUserInput resets the input string to "0"
     */
    public void resetUserInput()
    {
        USER_TEXT = "0";
    }

    /**
     * getChar gets the char that the user entered
     * @return USER_TEXT.charAt(0)
     */
    public char getChar()
    {
        return USER_TEXT.charAt(0);
    }
}
