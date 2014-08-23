import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by zam on 8/19/2014.
 */
public class MainMenu extends JPanel {

    private Image imageMenu;
    private Image imageArrow;
    private int imageArrowX = 200;
    private int imageArrowY = 190;

    public MainMenu() {
        ImageIcon imageIcon;
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/spaceshootMainmenu.png"));
        imageMenu = imageIcon.getImage();

        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/arrow.png"));
        imageArrow = imageIcon.getImage();
    }

    public void moveArrow() {
        if(imageArrowY == 190) {
            imageArrowY = 215;
        }
        else {
            imageArrowY = 190;
        }
        repaint();
    }

    public boolean isStart() {
        if (imageArrowY == 190) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(imageMenu, 0, 0, null);
        g.drawImage(imageArrow, imageArrowX, imageArrowY, null);
    }

}
