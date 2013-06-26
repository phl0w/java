package org.phl0w.aurora.itmaples.utilities;

import ms.aurora.api.methods.Calculations;
import ms.aurora.api.methods.Objects;
import ms.aurora.api.methods.Players;
import ms.aurora.api.methods.tabs.Equipment;
import ms.aurora.api.methods.tabs.Inventory;
import ms.aurora.api.wrappers.GameObject;
import ms.aurora.api.wrappers.WidgetItem;

import java.util.Arrays;

public class Methods {

    private static final int[] HATCHET_IDS = {1349, 1351, 1353, 1355, 1357, 1359, 6739};

    public static GameObject getNearest(final int id) {
        final GameObject[] loadedObjects = Objects.getAll();
        GameObject nearest = null;
        double distance = Double.MAX_VALUE;
        for (final GameObject o : loadedObjects) {
            double tempDir;
            if ((tempDir = Calculations.distance(o.getLocation(), Players.getLocal().getLocation())) < distance) {
                distance = tempDir;
                nearest = o;
            }
        }
        return nearest;
    }

    public static boolean hasHatchet() {
        for (final WidgetItem i : Inventory.getAll()) {
            if (inArray(i.getId(), HATCHET_IDS)) {
                return true;
            }
        }
        for (final WidgetItem i : Equipment.getItems()) {
            if (inArray(i.getId(), HATCHET_IDS)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHatchet(final int id) {
        return inArray(id, HATCHET_IDS);
    }

    private static boolean inArray(final int id, final int[] array) {
        Arrays.sort(array);
        return Arrays.binarySearch(array, id) >= 0;
    }

}