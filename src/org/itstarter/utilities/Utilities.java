package org.itstarter.utilities;

import org.itstarter.utilities.interfaces.Condition;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;

public class Utilities {


    public static Item[] getItems(final int... id) {
        return Inventory.getItems(new Filter<Item>() {
            @Override
            public boolean accept(final Item i) {
                for (int i1 : id) {
                    if (i.getId() != i1) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

    public static boolean waitFor(final Condition c, final long timeout) {
        final Timer t = new Timer(timeout);
        while (t.isRunning() && !c.activate()) {
            Task.sleep(20);
        }
        return c.activate();
    }

}
