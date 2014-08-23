import javax.swing.*;
import java.awt.*;

/**
 * Created by zam on 8/19/2014.
 */
public class PlayerShip {

    public int x;
    public int y;
    public Image image;

    public PlayerShip() {
        ImageIcon imageIcon;
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/ship.png"));
        image = imageIcon.getImage();
        x = 0;
        y = 0;
    }

    public PlayerShip(int x2, int y2) {
        ImageIcon imageIcon;
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/ship.png"));
        image = imageIcon.getImage();

        x = x2;
        y = y2;
    }

    public void moveLeft() {
        if(x >= 0)
            x -= 6;
    }

    public void moveRight() {
        if(x <= 417)
            x += 6;
    }

    public void moveDown() {
        y -= 6;
    }

    public void moveUp() {
        y += 6;
    }

    public void setX(int i) {
        x = i;
    }

    public void setY(int i) {
        y = i;
    }

}
