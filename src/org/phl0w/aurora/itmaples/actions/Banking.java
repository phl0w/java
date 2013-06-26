package org.phl0w.aurora.itmaples.actions;

import ms.aurora.api.methods.Players;
import ms.aurora.api.methods.Walking;
import ms.aurora.api.methods.tabs.Bank;
import ms.aurora.api.methods.tabs.Inventory;
import ms.aurora.api.script.Action;
import ms.aurora.api.util.Predicate;
import ms.aurora.api.util.StatePredicate;
import ms.aurora.api.util.Utilities;
import ms.aurora.api.wrappers.Tile;
import ms.aurora.api.wrappers.WidgetItem;
import org.phl0w.aurora.itmaples.iTMaples;
import org.phl0w.aurora.itmaples.utilities.Methods;
import org.phl0w.aurora.itmaples.utilities.Variables;

public class Banking extends Action {

    private static final Tile BANK_TILE = new Tile(2724, 3492, 0);

    private static final Predicate<WidgetItem> BANK_PREDICATE = new Predicate<WidgetItem>() {
        @Override
        public boolean apply(final WidgetItem i) {
            return Methods.isHatchet(i.getId());
        }
    };

    private static final StatePredicate SLEEP = new StatePredicate() {
        @Override
        public boolean apply() {
            return Players.getLocal().isMoving();
        }
    };


    @Override
    public boolean activate() {
        return Inventory.isFull();
    }

    @Override
    public int execute() {
        if (Bank.open()) {
            Variables.status = "Banking";
            Bank.depositAllExcept(BANK_PREDICATE);
            if (Bank.close()) {
                iTMaples.log.info("Going 2 trees");
            }
        } else {
            Variables.status = "Walking bank";
            Walking.walkTo(BANK_TILE);
            Utilities.sleepNoException(500, 700);
            Utilities.sleepUntil(SLEEP, 2000);
        }
        return 1000;
    }
}
