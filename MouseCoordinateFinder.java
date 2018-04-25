import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseCoordinateFinder extends MouseAdapter {
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("You clicked mouse at coordinates " + e.getX() + ", " + e.getY());
    }
}
