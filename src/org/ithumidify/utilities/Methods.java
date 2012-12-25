package org.ithumidify.utilities;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.util.Timer;

public class Methods {

    public static boolean waitFor(final Condition c, final long timeout) {
        final Timer t = new Timer(timeout);
        while (t.isRunning() && !c.validate()) {
            Task.sleep(20, 30);
        }
        return c.validate();
    }

}
