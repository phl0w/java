package org.phl0w.itherblore.nodes.mix;

import org.phl0w.framework.node.impl.PriorityNode;
import org.phl0w.framework.node.util.Priority;
import org.phl0w.itherblore.utilities.Methods;
import org.phl0w.itherblore.utilities.Utilities;
import org.phl0w.itherblore.utilities.Variables;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Item;

/**
 * iTHerblore 2
 * Mix.java
 * Purpose: a node that handles the mixing of ingredients for us.
 *
 * @author _phl0w
 * @version 1.1
 * @since 20/04/2013
 */
public class Mix extends PriorityNode {

    private MethodContext ctx = null;
    private Utilities utilities = null;

    public Mix(final MethodContext ctx) {
        super(ctx);
        this.ctx = ctx;
        utilities = new Utilities(ctx);
    }

    @Override
    public Priority priority() {
        return Priority.DEFAULT;
    }

    @Override
    public boolean activate() {
        return !utilities.needIngredients() && !ctx.bank.isOpen() && !ctx.players.getLocal().isInMotion()
                && !ctx.widgets.get(1251, 32).isValid();
    }

    @Override
    public void execute() {
        final Component button = ctx.widgets.get(1370, 38);
        if (button.isValid()) {
            if (button.click(true)) {
                Methods.waitFor(utilities.sleepForMakeScreen);
            }
        } else {
            final Item primary = utilities.getItem(Variables.primary);
            final Item secondary = utilities.getItem(Variables.secondary);
            if (primary != null && secondary != null) {
                if (primary.getComponent().interact("Use")) {
                    if (ctx.inventory.getSelectedItem() != null && secondary.getComponent().click(true)) {
                        Methods.waitFor(utilities.sleepForGui);
                    }
                }
            }
        }
    }
}
