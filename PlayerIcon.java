import javax.swing.*;
import java.awt.*;

    public class PlayerIcon {

        private int dx;
        private int dy;
        private int x = 510 ;
        private int y = 530;
        private int w;
        private int h;
        private Image image;


        public PlayerIcon() {

            loadImage();
        }


        private void loadImage() {

            ImageIcon ii = new ImageIcon("C:\\Users\\paris\\IdeaProjects\\zagopoly gui\\src\\playerIcon.png");
            image = ii.getImage();

            w = image.getWidth(null);
            h = image.getHeight(null);
        }

        public void move(Board theBoard, Player currentPlayer) {
            Property tempProperty = theBoard.getSquare(currentPlayer.currentSquare());
            int moveToX = tempProperty.getX();
            int moveToY = tempProperty.getY();
            x = moveToX;
            y = moveToY;
        }

        public int getX() {

            return x;
        }

        public int getY() {

            return y;
        }

        public int getWidth() {

            return w;
        }

        public int getHeight() {

            return h;
        }

        public Image getImage() {
            return image;
        }
    }
