import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameBoardUI extends JPanel{
    private PlayerIcon gamePiece;

    public GameBoardUI(PlayerIcon Icon){
        gamePiece = Icon;
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(gamePiece.getImage(), gamePiece.getX(),gamePiece.getY(), this);
    }

    public void step(Board gameBoard, Player currentPlayer) {
        gamePiece.move(gameBoard, currentPlayer);
        repaint(getX(),getY(),getWidth(),getHeight());
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
