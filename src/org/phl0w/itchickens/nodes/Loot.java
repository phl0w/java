package org.phl0w.itchickens.nodes;

import org.phl0w.framework.node.impl.PriorityNode;
import org.phl0w.framework.node.util.Priority;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GroundItem;

public class Loot extends PriorityNode {

    private MethodContext ctx = null;
    private static final int FEATHER_ID = 337;

    public Loot(final MethodContext context) {
        super(context);
    }

    @Override
    public boolean activate() {
        return ctx != null && !ctx.groundItems.select().id(FEATHER_ID).nearest().isEmpty();
    }

    @Override
    public void execute() {
        for (final GroundItem item : ctx.groundItems.select().id(FEATHER_ID).nearest().first()) {
            if (item != null) {
                if (item.isOnScreen()) {
                    if (item.interact("Take")) {
                        System.out.println("Took feather");
                    }
                } else {
                    ctx.camera.turnTo(item);
                    if (!item.isOnScreen()) {
                        ctx.movement.stepTowards(item);
                    }
                }
            }
        }

    }

    @Override
    public Priority priority() {
        return Priority.HIGH;
    }
}
