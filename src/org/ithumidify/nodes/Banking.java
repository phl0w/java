package org.ithumidify.nodes;

import org.ithumidify.utilities.Constants;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class Banking extends Node {

    @Override
    public boolean activate() {
        return Inventory.getCount(Constants.CLAY) == 0;
    }

    @Override
    public void execute() {
        final boolean depo = Inventory.getCount(Constants.SOFT_CLAY) > 0;
        Bank.open();
        if (depo) {
            if (Bank.deposit(Constants.SOFT_CLAY, 0)) {
            }
        }
        if (Bank.withdraw(Constants.CLAY, 0)) {
            Bank.close();
        }
    }
}
