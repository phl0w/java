package org.phl0w.itherblore.utilities;

import org.phl0w.itherblore.utilities.interfaces.Condition;
import org.powerbot.script.lang.ItemQuery;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Item;

/**
 * iTHerblore 2
 * Utilities.java
 * Purpose: contains methods that require an instance of the MethodContext
 *
 * @author _phl0w
 * @version 1.0
 * @since 24/06/13
 */
public class Utilities extends MethodProvider {

    private MethodContext ctx = null;

    public Utilities(final MethodContext methodContext) {
        super(methodContext);
        ctx = methodContext;
    }

    public boolean needIngredients() {
        return getCount(Variables.primary) == 0 || (Variables.secondary != -1 && getCount(Variables.secondary) == 0);
    }

    private int getCount(final int item) {
        return ctx.inventory.select().id(item).count();
    }

    public Item getItem(final int i1) {
        final ItemQuery<Item> item = ctx.inventory.select().id(i1);
        return item.iterator().next();
    }

    public final Condition sleepForGui = new Condition() {
        @Override
        public boolean validate() {
            return ctx.widgets.get(1370, 38).isValid();
        }
    };
    public final Condition sleepForMakeScreen = new Condition() {
        @Override
        public boolean validate() {
            return ctx.widgets.get(1251, 32).isValid();
        }
    };

}
