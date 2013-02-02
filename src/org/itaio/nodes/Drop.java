package org.itaio.nodes;

import org.itaio.utilities.Utilities;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class Drop extends Node {

    private int[] dropId;

    public Drop(final int... drop) {
        this.dropId = drop;
    }

    @Override
    public boolean activate() {
        return Inventory.isFull();
    }

    @Override
    public void execute() {
        for (final Item i : Utilities.getItems(dropId)) {
            if (i.getWidgetChild().interact("Drop")) {
                Task.sleep(10);
            }
        }
    }
}
