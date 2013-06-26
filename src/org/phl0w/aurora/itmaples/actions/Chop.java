package org.phl0w.aurora.itmaples.actions;

import ms.aurora.api.methods.*;
import ms.aurora.api.methods.tabs.Inventory;
import ms.aurora.api.script.Action;
import ms.aurora.api.util.Predicate;
import ms.aurora.api.util.StatePredicate;
import ms.aurora.api.util.Utilities;
import ms.aurora.api.wrappers.GameObject;
import ms.aurora.api.wrappers.Tile;
import org.phl0w.aurora.itmaples.utilities.Methods;
import org.phl0w.aurora.itmaples.utilities.Variables;

public class Chop extends Action {

    private static final Predicate<GameObject> TREE_PREDICATE = new Predicate<GameObject>() {
        @Override
        public boolean apply(final GameObject obj) {
            return obj.getId() == 2782 && Npcs.find().location(obj.getLocation()).result().length == 0;
        }
    };

    private static final StatePredicate SLEEP = new StatePredicate() {
        @Override
        public boolean apply() {
            return Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1;
        }
    };

    private static final StatePredicate WALK_SLEEP = new StatePredicate() {
        @Override
        public boolean apply() {
            return Players.getLocal().isMoving();
        }
    };

    private static final Tile MAPLE_TREE_TILE = new Tile(2728, 3500, 0);

    @Override
    public boolean activate() {
        return !Inventory.isFull() &&
                Methods.hasHatchet() && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public int execute() {
        final GameObject g = Objects.get(TREE_PREDICATE);
        if (g != null) {
            if (g.isOnScreen()) {
                if (g.applyAction("Chop")) {
                    Variables.status = "Chopping";
                    Utilities.sleepNoException(500, 700);
                    Utilities.sleepUntil(SLEEP, 2000);
                }
            } else {
                Variables.status = "Turning cam";
                Camera.turnTo(g);
                if (!g.isOnScreen() && Calculations.distance(Players.getLocal().getLocation(), MAPLE_TREE_TILE) >= 5) {
                    Variables.status = "Walking tree";
                    Walking.walkTo(MAPLE_TREE_TILE);
                    Utilities.sleepNoException(500, 700);
                    Utilities.sleepUntil(WALK_SLEEP, 2000);
                }
            }
        }
        return 1000;
    }
}