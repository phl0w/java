package org.ithumidify.nodes;

import org.ithumidify.utilities.Condition;
import org.ithumidify.utilities.Constants;
import org.ithumidify.utilities.Methods;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class Humidify extends Node {

    @Override
    public boolean activate() {
        return !Bank.isOpen() && Inventory.getCount(Constants.CLAY) > 0 && Inventory.getCount(Constants.SOFT_CLAY) == 0 && Inventory.getCount(Constants.ASTRAL_RUNE) > 0 && Players.getLocal().getAnimation() == -1 && Widgets.get(640, 1).visible() && Widgets.get(640, 32).getTextureId() == 14437 && Widgets.get(137, 56).getText().contains("[Press");
    }

    @Override
    public void execute() {
        Keyboard.sendKey('1');
        Methods.waitFor(new Condition() {
            @Override
            public boolean validate() {
                return Players.getLocal().getAnimation() != -1 || Inventory.getCount(Constants.SOFT_CLAY) > 0;
            }
        }, 2000);
    }
}
