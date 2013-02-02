package org.itaio.utilities;

import org.itaio.utilities.interfaces.Condition;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;

import java.util.ArrayList;

public class Utilities {


    public static Item[] getItems(final int... id) {
        final ArrayList<Item> array = new ArrayList<Item>();
        for (final Item i : Inventory.getItems()) {
            for (int i1 : id) {
                if (i.getId() == i1) {
                    array.add(i);
                }
            }
        }
        final Item[] items = new Item[array.size()];
        return array.toArray(items);
    }

    public static boolean waitFor(final Condition c, final long timeout) {
        final Timer t = new Timer(timeout);
        while (t.isRunning() && !c.activate()) {
            Task.sleep(20);
        }
        return c.activate();
    }

    public static boolean sleepIfMoving() {
        if (Walking.getDestination() != null) {
            if (Calculations.distanceTo(Walking.getDestination()) > 5) {
                return true;
            }
        }
        return false;
    }

}
