package org.phl0w.itchickens.utilities;

import org.phl0w.itchickens.utilities.interfaces.Condition;
import org.powerbot.script.util.Delay;
import org.powerbot.script.util.Timer;

public class Methods {

    public static boolean waitFor(final Condition c, final long... timeout) {
        final Timer t = new Timer(timeout.length == 1 ? timeout[0] : 2000);
        while (t.isRunning() && !c.validate()) {
            Delay.sleep(50, 70);
        }
        return c.validate();
    }
}
