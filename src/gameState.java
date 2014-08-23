import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by zam on 8/19/2014.
 */
public class gameState extends JPanel implements KeyListener, Runnable {

    PlayerShip player;
    Image background;
    String actionTaken = null;
    Thread runner;
    ArrayList<Enemy> enemy;
    ArrayList<bullet> bullet;
    int enemyTimer;
    int lifeCounter = 0;
    int setEnemyInterval = 30;
    boolean pause = false;

    public gameState() {
        //Add Player
        player = new PlayerShip();
        player.setX(0);
        player.setY(420);

        ImageIcon imageIcon;
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/background.png"));
        background = imageIcon.getImage();

        runner = new Thread(this, "Game");
        runner.start();
        enemy = new ArrayList<Enemy>();
        bullet = new ArrayList<bullet>();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(lifeCounter),383,22);
        g.drawString(String.valueOf(player.x),356,85);
        g.drawString(String.valueOf(player.y),356,115);
        g.drawImage(player.image, player.x, player.y, null);
        if(!enemy.isEmpty()) {
            for (Enemy e : enemy) {
                g.drawImage(e.image, e.x, e.y, null);
            }
        }
        if(!bullet.isEmpty()) {
            for (bullet b : bullet) {
                g.drawImage(b.image, b.x, b.y, null);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            actionTaken = "moveLeft";
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            actionTaken = "moveRight";
        if (e.getKeyCode() == KeyEvent.VK_UP)
            actionTaken = "moveUp";
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            actionTaken = "moveDown";
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Center of image
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            bullet.add(new bullet(player.x+20, player.y));

        if(e.getKeyCode() == KeyEvent.VK_P) {
            if(pause)
                pause = false;
            else
                pause = true;
        }


        actionTaken = null;
    }

    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 50;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        makeEnemy();

        while(true) {
            //Game loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            if(!pause) {
                if (actionTaken == "moveLeft")
                    player.moveLeft();
                if (actionTaken == "moveRight") {
                    player.moveRight();
                }
                if (actionTaken == "moveUp") {
                    player.moveUp();
                }
                if (actionTaken == "moveDown") {
                    player.moveDown();
                }
                updateEnemy();
                updateBullet();
                bulletAndEnemyBounds();
            }
            //actionTaken = null;

            try{
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
                validate();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void makeEnemy() {
        Random rand = new Random();
        int randomNum = rand.nextInt(250);
        enemy.add(new Enemy("Zamight", randomNum, -60));
    }

    public void updateEnemy() {

        boolean addMoreEnemy = false;

        if (!enemy.isEmpty()) {
            for (Iterator<Enemy> it = enemy.iterator(); it.hasNext(); ) {
                Enemy e = it.next();
                e.move();
                if (e.kill() || checkEnemyBoundsHit(e)) {
                    it.remove();
                }
            }
        }

        if(enemyTimer >= setEnemyInterval) {
            makeEnemy();
            enemyTimer = 0;
        }

        enemyTimer++;
    }

    public boolean checkEnemyBoundsHit(Enemy e) {

        if(e.x >= player.x-51 && e.x <= player.x+51) {
            if (e.y >= player.y-50) {
                lifeCounter++;
                return true;
            }
        }

        return false;
    }

    public void bulletAndEnemyBounds() {
        if(!enemy.isEmpty()) {
            for (Iterator<Enemy> it = enemy.iterator(); it.hasNext(); ) {
                Enemy e = it.next();
                e.move();
                for (Iterator<bullet> bl = bullet.iterator(); bl.hasNext(); ) {
                    bullet b = bl.next();
                    if (e.x >= b.x - 51 && e.x <= b.x + 51) {
                        if (e.y >= b.y - 50) {
                            bl.remove();
                            it.remove();
                            setEnemyInterval--;
                            System.out.println(setEnemyInterval);
                            break;
                        }
                    }
                }
            }
        }

    }



    public void updateBullet() {

        if (!bullet.isEmpty()) {
            for (Iterator<bullet> it = bullet.iterator(); it.hasNext(); ) {
                bullet b = it.next();
                b.move();
                if (b.kill()) {
                    it.remove();
                }
            }
        }
    }
}
