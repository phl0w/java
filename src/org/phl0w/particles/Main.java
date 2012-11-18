package org.phl0w.particles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {

    private static Main m;
    private static final Random<Color> c = new Random<Color>(Color.BLACK, Color.RED, Color.YELLOW, Color.CYAN);
    private static Sprite[] sprites = new Sprite[10];
    private static Sprite[] bkup;

    public Main() {
        super("Particles");
        setSize(200, 200);
        addKeyListener(this);
        setVisible(true);
        add(new Applet());
    }

    public static void refreshSprites() {
        bkup = sprites;
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Sprite(new Location(getRandom(100), getRandom(100)), c.getRandom(), 8);
        }
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
            System.out.println("h");
            refreshSprites();
            System.out.println(sprites.equals(bkup));
        }
    }

    private class Applet extends JPanel {
        @Override
        public void paintComponent(final Graphics g) {
            for (Sprite s : sprites) {
                s.draw(g);
            }
        }
    }

    private static final java.util.Random r = new java.util.Random();

    public static int getRandom(int max) {
        return r.nextInt(max);
    }
}
