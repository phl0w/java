package org.phl0w.itchickens.nodes;

import org.phl0w.framework.node.impl.PriorityNode;
import org.phl0w.framework.node.util.Priority;
import org.phl0w.itchickens.utilities.Methods;
import org.phl0w.itchickens.utilities.interfaces.Condition;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Filter;
import org.powerbot.script.wrappers.Npc;

public class Attack extends PriorityNode {

    private static final Filter<Npc> CHICKEN_FILTER = new Filter<Npc>() {
        @Override
        public boolean accept(final Npc n) {
            return !n.isInCombat() && n.getHealthPercent() == 100;
        }
    };

    public Attack(final MethodContext context) {
        super(context);
    }

    @Override
    public boolean activate() {
        return ctx != null && !ctx.players.getLocal().isInCombat();
    }

    @Override
    public void execute() {
        for (final Npc n : ctx.npcs.select().name("chicken").select(CHICKEN_FILTER).nearest().first()) {
            if (n.interact("Attack")) {
                Methods.waitFor(new Condition() {
                    @Override
                    public boolean validate() {
                        return ctx.players.getLocal().isInCombat();
                    }
                });
            }
        }
    }

    @Override
    public Priority priority() {
        return Priority.DEFAULT;
    }
}
