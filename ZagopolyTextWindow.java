
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ZagopolyTextWindow extends JPanel implements ActionListener
{
    private JTextField TEXT_FIELD;
    private JTextArea TEXT_AREA;
    private final static String newLine = "\n";

    /**
     * constructor for class ZagopolyTextWindow
     */
    public ZagopolyTextWindow()
    {
        super(new GridBagLayout());

        TEXT_FIELD = new JTextField(20);
        TEXT_FIELD.addActionListener(this);

        TEXT_AREA = new JTextArea(5, 20);
        TEXT_AREA.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(TEXT_AREA);

        // Add components to this panel
        GridBagConstraints Constraints = new GridBagConstraints();
        Constraints.gridwidth = GridBagConstraints.REMAINDER;

        Constraints.fill = GridBagConstraints.HORIZONTAL;
        add(TEXT_FIELD, Constraints);

        Constraints.fill = GridBagConstraints.BOTH;
        Constraints.weightx = 1.0;
        Constraints.weighty = 1.0;
        add(scrollPane, Constraints);
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
}
