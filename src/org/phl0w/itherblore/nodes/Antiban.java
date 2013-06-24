package org.phl0w.itherblore.nodes;

import org.phl0w.framework.node.impl.PriorityNode;
import org.phl0w.framework.node.util.Priority;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Delay;
import org.powerbot.script.util.Random;

import java.awt.*;

/**
 * iTHerblore 2
 * Antiban.java
 * Purpose: a node that handles the antiban for us
 *
 * @author _phl0w
 * @version 1.1
 * @since 18/04/13
 */
public class Antiban extends PriorityNode {

    private MethodContext ctx = null;

    public Antiban(final MethodContext ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    @Override
    public boolean activate() {
        return ctx.players.getLocal().getAnimation() != -1;
    }

    @Override
    public void execute() {
        final int t = Random.nextInt(0, 20);
        switch (t) {
            case 0:
            case 1:
            case 2:
                ctx.camera.setAngle(Random.nextInt(20, 300));
                break;
            case 3:
            case 4:
                ctx.camera.setPitch(Random.nextInt(0, 100));
                break;
            case 6:
                doMouseStuff();
                break;
            case 7:
            case 8:
            case 9:
                final Point p = ctx.mouse.getLocation();
                ctx.mouse.move(new Point(Random.nextInt(-50, 50) + p.x, Random.nextInt(-50, 50) + p.y));
                break;
        }
        Delay.sleep(1000);
    }

    @Override
    public Priority priority() {
        return Priority.LOW;
    }

    private void doMouseStuff() {
        final boolean r = Random.nextBoolean();
        if (r && ctx.menu.isOpen()) {
            ctx.menu.click("Cancel");
        } else {
            final double height = ctx.game.getDimensions().getHeight();
            final double width = ctx.game.getDimensions().getWidth();
            ctx.mouse.move(new Point(Random.nextInt(0, (int) height), Random.nextInt(0, (int) width)));
        }
    }
}