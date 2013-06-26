package org.phl0w.aurora.itmaples.actions;

import ms.aurora.api.methods.Camera;
import ms.aurora.api.methods.Objects;
import ms.aurora.api.methods.Players;
import ms.aurora.api.methods.Walking;
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

    private static final int MAPLE_TREE_ID = 2782;

    private static final Predicate<GameObject> TREE_PREDICATE = new Predicate<GameObject>() {
        @Override
        public boolean apply(final GameObject obj) {
            return obj.getId() == MAPLE_TREE_ID;
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
                if (!g.isOnScreen()) {
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