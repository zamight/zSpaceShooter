import javax.swing.*;
import java.awt.*;

/**
 * Created by zam on 8/22/2014.
 */
public class bullet {

    int x = 0;
    int y = 0;
    int speed = 3;
    public Image image;

    public bullet(int x1, int y1) {
        x = x1;
        y = y1;

        ImageIcon imageIcon;
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/bullet.png"));
        image = imageIcon.getImage();
    }

    public void move() {
        y -= speed;
    }

    public boolean kill() {
        if(y <= -30) {
            return true;
        }
        return false;
    }
}
