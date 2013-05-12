package org.phl0w.itherblore.nodes;

import org.phl0w.itherblore.utilities.Methods;
import org.phl0w.itherblore.utilities.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.widget.Bank;

/**
 * iTHerblore 2
 * Banking.java
 * Purpose: a node that handles the banking for us.
 *
 * @author _phl0w
 * @version 1.2
 * @since 18/04/13
 */
public class Banking extends Node {

    @Override
    public boolean activate() {
        return Methods.needIngredients() && !Widgets.get(1251, 32).validate();
    }

    @Override
    public void execute() {
        if (Bank.open()) {
            if (Bank.depositInventory()) {
                final boolean twoItems = Variables.secondary != -1;
                if (twoItems) {
                    if (Bank.withdraw(Variables.primary, 14) && Bank.withdraw(Variables.secondary, 0)) {
                        System.out.println("Successfully withdrew primary & secondary ingredient.");
                    } else {
                        Bank.close();
                        System.out.println("No resources left, logging.");
                        Game.logout(true);
                    }
                } else {
                    if (Bank.withdraw(Variables.primary, 0)) {
                        System.out.println("Successfully withdrew primary ingredient.");
                    } else {
                        Bank.close();
                        System.out.println("No resources left, logging.");
                        Game.logout(true);
                    }
                }
            }
            Bank.close();
        }
    }
}
