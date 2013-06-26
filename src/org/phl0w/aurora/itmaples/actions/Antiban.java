package org.phl0w.aurora.itmaples.actions;

import ms.aurora.api.methods.Camera;
import ms.aurora.api.methods.Menu;
import ms.aurora.api.methods.tabs.Tabs;
import ms.aurora.api.script.Action;
import ms.aurora.api.util.StatePredicate;
import ms.aurora.api.util.Utilities;
import ms.aurora.input.VirtualMouse;
import org.phl0w.aurora.itmaples.utilities.Variables;

public class Antiban extends Action {

    private static final StatePredicate SLEEP = new StatePredicate() {
        @Override
        public boolean apply() {
            return Tabs.isOpen(Tabs.Tab.STATS);
        }
    };

    @Override
    public boolean activate() {
        return Utilities.random(0, 100) > 95;
    }

    @Override
    public int execute() {
        Variables.status = "AB";
        final int r = Utilities.random(0, 11);
        switch (r) {
            case 0:
            case 1:
            case 2:
                final boolean ran = Utilities.random(0, 2) == 1;
                if (ran && Menu.isMenuOpen()) {
                    Menu.click("Cancel");
                } else {
                    VirtualMouse.clickMouse(false);
                    Utilities.sleepNoException(600, 800);
                    Menu.click("Cancel");
                }
                break;
            case 3:
            case 4:
            case 5:
                Tabs.openTab(Tabs.Tab.STATS);
                Utilities.sleepUntil(SLEEP, 800);
                VirtualMouse.moveMouse(712 + Utilities.random(0, 4), 377 + Utilities.random(0, 4));
                Utilities.sleepNoException(1000, 2000);
                Tabs.openTab(Tabs.Tab.INVENTORY);
                break;
            case 6:
            case 7:
            case 8:
                Camera.setAngle(Utilities.random(0, 360));
                break;
            default:
                Camera.setPitch(Utilities.random(10, 200));
                break;
        }
        return 1000;
    }
}
