package org.itaio.nodes;

import org.itaio.utilities.Constants;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class Mine extends Node {

    @Override
    public boolean activate() {
        return Players.getLocal().isIdle() && !Inventory.isFull();
    }

    @Override
    public void execute() {
        final int tinCount = Inventory.getCount(Constants.TIN_ORE);
        final int copperCount = Inventory.getCount(Constants.COPPER_ORE);
    }
}
