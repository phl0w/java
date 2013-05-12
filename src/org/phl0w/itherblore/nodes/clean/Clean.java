package org.phl0w.itherblore.nodes.clean;

import org.phl0w.itherblore.utilities.Methods;
import org.phl0w.itherblore.utilities.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * iTHerblore 2
 * Clean.java
 * Purpose: a node that handles the cleaning of grimy herbs for us.
 *
 * @author _phl0w
 * @version 1.0
 * @since 18/04/2013
 */
public class Clean extends Node {


    @Override
    public boolean activate() {
        return !Methods.needIngredients() && !Bank.isOpen() && !Widgets.get(1251, 32).validate();
    }

    @Override
    public void execute() {
        final WidgetChild button = Widgets.get(1370, 38);
        if (button.validate()) {
            if (button.click(true)) {
                Methods.waitFor(Variables.SLEEP_FOR_MAKE_SCREEN);
            }
        } else {
            final Item herb = Inventory.getItem(Variables.primary);
            if (herb != null) {
                if (herb.getWidgetChild().interact("Clean")) {
                    Methods.waitFor(Variables.SLEEP_FOR_GUI);
                }
            }
        }
    }
}
