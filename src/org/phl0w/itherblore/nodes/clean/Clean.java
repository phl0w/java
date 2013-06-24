package org.phl0w.itherblore.nodes.clean;

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
 * Clean.java
 * Purpose: a node that handles the cleaning of grimy herbs for us.
 *
 * @author _phl0w
 * @version 1.1
 * @since 18/04/2013
 */
public class Clean extends PriorityNode {

    private MethodContext ctx = null;
    private Utilities utilities = null;


    public Clean(final MethodContext ctx) {
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
        return !utilities.needIngredients() && !ctx.bank.isOpen() && !ctx.widgets.get(1251, 32).isValid();
    }

    @Override
    public void execute() {
        final Component button = ctx.widgets.get(1370, 38);
        if (button.isValid()) {
            if (button.click(true)) {
                Methods.waitFor(utilities.sleepForMakeScreen);
            }
        } else {
            final Item herb = utilities.getItem(Variables.primary);
            if (herb != null) {
                if (herb.getComponent().interact("Clean")) {
                    Methods.waitFor(utilities.sleepForGui);
                }
            }
        }
    }
}
