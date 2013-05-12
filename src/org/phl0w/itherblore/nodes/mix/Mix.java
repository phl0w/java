package org.phl0w.itherblore.nodes.mix;

import org.phl0w.itherblore.utilities.Methods;
import org.phl0w.itherblore.utilities.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * iTHerblore 2
 * Mix.java
 * Purpose: a node that handles the mixing of ingredients for us.
 *
 * @author _phl0w
 * @version 1.0
 * @since 20/04/2013
 */
public class Mix extends Node {

    @Override
    public boolean activate() {
        return !Methods.needIngredients() && !Bank.isOpen() && Players.getLocal().isIdle()
                && !Widgets.get(1251, 32).validate();
    }

    @Override
    public void execute() {
        final WidgetChild button = Widgets.get(1370, 38);
        if (button.validate()) {
            if (button.click(true)) {
                Methods.waitFor(Variables.SLEEP_FOR_MAKE_SCREEN);
            }
        } else {
            final Item primary = Inventory.getItem(Variables.primary);
            final Item secondary = Inventory.getItem(Variables.secondary);
            if (primary != null && secondary != null) {
                if (primary.getWidgetChild().interact("Use")) {
                    if (Inventory.getSelectedItem() != null && secondary.getWidgetChild().click(true)) {
                        Methods.waitFor(Variables.SLEEP_FOR_GUI);
                    }
                }
            }
        }
    }
}
