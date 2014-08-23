import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by zam on 8/19/2014.
 */
public class starter extends JFrame {

    public MainMenu mainmenu;

    public starter() {
        setTitle("Tester");
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(468, 499);
        setResizable(false);

        mainmenu = new MainMenu();
        add(mainmenu);
        setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (mainmenu.isStart()) {
                        remove(mainmenu);
                        gameState gamestate = new gameState();
                        add(gamestate);
                        revalidate();
                        repaint();
                        addKeyListener(gamestate);
                    }
                }
                else {
                    mainmenu.moveArrow();
                }
            }
        });

    }

    public static void main(String[] Args) {
        new starter();
    }

}
