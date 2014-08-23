import javax.swing.*;
import java.awt.*;

/**
 * Created by zam on 8/19/2014.
 */
public class Enemy {

    public String name;
    public int x;
    public int y;
    public Image image;

    public Enemy(String enemyname) {
        name = enemyname;
        x = 0;
        y = 50;

        ImageIcon imageIcon;
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/ufo.png"));
        image = imageIcon.getImage();
    }

    public Enemy(String enemyname, int x2, int y2) {
        ImageIcon imageIcon;
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/ufo.png"));
        image = imageIcon.getImage();

        x = x2;
        y = y2;
    }

    public void move() {
        y += 3;
    }

    public static void main(String[] args) {
        new Enemy(args[0]);
    }

    public boolean kill() {
        if(y >= 499) {
            return true;
        }
        return false;
    }
}
