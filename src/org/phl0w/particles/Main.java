package org.phl0w.particles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {

    private static Main m;
    private static final Random<Color> c = new Random<Color>(Color.BLACK, Color.RED, Color.YELLOW, Color.CYAN);
    private static Sprite[] sprites = new Sprite[10];
    private static Sprite[] bkup;
    private static Sprite focussed;
    private static Sprite last;

    public Main() {
        super("Particles");
        setSize(200, 200);
        addKeyListener(this);
        new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        }).start();
        setLocationRelativeTo(null);
        setVisible(true);
        add(new Applet());
    }

    public static void refreshSprites() {
        bkup = sprites;
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Sprite(new Location(getRandom(100), getRandom(100)), c.getRandom(), 8);
        }
        focussed = sprites[getRandom(sprites.length - 1)];
    }

    public static void main(String[] args) {
        m = new Main();
        refreshSprites();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_K) {
            System.out.println("quitting");
            System.exit(-1);
            m.dispose();
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            refreshSprites();
            focussed = sprites[getRandom(sprites.length - 1)];
        } else if (e.getKeyCode() == KeyEvent.VK_F) {
            if (getClosest() == null) {
                System.out.println(":l");
            } else {
                focussed = getClosest();
            }
        }

    }

    private class Applet extends JPanel {

        @Override
        public void paintComponent(final Graphics g) {
            for (Sprite s : sprites) {
                s.draw(g);
            }
            if (focussed != null) {
                g.setColor(Color.BLUE);
                g.drawString("focussed", focussed.loc.getX(), focussed.loc.getY());
            }
        }
    }

    private static final java.util.Random r = new java.util.Random();

    public static int getRandom(int max) {
        return r.nextInt(max);
    }

    public static Sprite getClosest() {
        Sprite retSprite = null;
        for (Sprite s : sprites) {
            double dis = 100;
            if (distance(s.loc, focussed.loc) < dis) {
                dis = distance(s.loc, focussed.loc);
                retSprite = s;
            }
        }
        return retSprite;
    }

    public static double distance(Location l1, Location l2) {
        return Math.sqrt(Math.pow(l1.getX() - l2.getX(), 2) + Math.pow(l1.getY() - l2.getY(), 2));
    }
}