package org.ithumidify.nodes;

import org.ithumidify.utilities.Condition;
import org.ithumidify.utilities.Methods;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class OpenAbilities extends Node {

    @Override
    public boolean activate() {
        return !Bank.isOpen() && !Widgets.get(640, 1).visible();
    }

    @Override
    public void execute() {
        final WidgetChild wc = Widgets.get(640, 3);
        if (wc.validate()) {
            if (wc.interact("Expand")) {
                Methods.waitFor(new Condition() {
                    @Override
                    public boolean validate() {
                        return Widgets.get(640, 1).visible();
                    }
                }, 2000);
            }
        }
    }
}
