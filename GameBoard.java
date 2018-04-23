import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameBoard extends JPanel{
    PlayerIcon Icon1 = new PlayerIcon();
    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Icon1.getImage(), Icon1.getX(),Icon1.getY(), this);
    }

    private void step(PlayerIcon Icon){
        Icon.move();
        repaint(Icon.getX()-1,Icon.getY()-1,Icon.getWidth()+2,Icon.getHeight()+2);
    }

    public void paintComponent(Graphics g) {
        try {
            BufferedImage Image = ImageIO.read(new File("C:\\Users\\paris\\IdeaProjects\\zagopoly gui\\src\\zagBoard.png"));
            super.paintComponent(g);
            g.drawImage(Image,0,0,null);
            doDrawing(g);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
