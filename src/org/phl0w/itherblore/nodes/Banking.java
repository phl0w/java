package org.phl0w.itherblore.nodes;

import org.phl0w.framework.node.impl.PriorityNode;
import org.phl0w.framework.node.util.Priority;
import org.phl0w.itherblore.utilities.Utilities;
import org.phl0w.itherblore.utilities.Variables;
import org.powerbot.script.methods.MethodContext;

/**
 * iTHerblore 2
 * Banking.java
 * Purpose: a node that handles the banking for us.
 *
 * @author _phl0w
 * @version 1.3
 * @since 18/04/13
 */
public class Banking extends PriorityNode {

    private MethodContext ctx = null;
    private Utilities utilities = null;

    public Banking(final MethodContext ctx) {
        super(ctx);
        this.ctx = ctx;
        utilities = new Utilities(ctx);
    }

    @Override
    public boolean activate() {
        return utilities.needIngredients() && !ctx.widgets.get(1251, 32).isValid();
    }

    @Override
    public void execute() {
        if (ctx.bank.open()) {
            if (ctx.bank.depositInventory()) {
                final boolean twoItems = Variables.secondary != -1;
                if (twoItems) {
                    if (ctx.bank.withdraw(Variables.primary, 14) && ctx.bank.withdraw(Variables.secondary, 0)) {
                        System.out.println("Successfully withdrew primary & secondary ingredient.");
                    } else {
                        ctx.bank.close();
                        System.out.println("No resources left.");
                    }
                } else {
                    if (ctx.bank.withdraw(Variables.primary, 0)) {
                        System.out.println("Successfully withdrew primary ingredient.");
                    } else {
                        ctx.bank.close();
                        System.out.println("No resources left.");
                    }
                }
            }
            ctx.bank.close();
        }
    }

    @Override
    public Priority priority() {
        return Priority.VERY_HIGH;
    }
}
