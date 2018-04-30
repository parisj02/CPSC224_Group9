/**
 * This class returns the coordinate of the mouse on the GUI
 * This file is for testing only
 * CPSC 224, Spring 2018
 * Group Project
 * @authors Connor Cooley, Jackson Paris, Nathan Vanos
 * @version 1.0, 04/18/2018
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseCoordinateFinder extends MouseAdapter {
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("You clicked mouse at coordinates " + e.getX() + ", " + e.getY());
    }
}
