package org.phl0w.particles;

import java.awt.*;

public class Sprite {

    public Location loc;
    public Color col;
    public int size;

    public Sprite(final Location loc, final Color col, final int size) {
        this.loc = loc;
        this.col = col;
        this.size = size;
    }

    public void draw(Graphics g) {
        g.setColor(col);
        g.fillOval(loc.getX(), loc.getY(), size, size);
    }

}
