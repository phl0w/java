package org.phl0w.itherblore.utilities.user;

import org.phl0w.itherblore.utilities.enums.Job;
import org.phl0w.itherblore.utilities.interfaces.Condition;
import org.powerbot.game.api.methods.Widgets;

import java.awt.*;

/**
 * iTHerblore 2
 * Variables.java
 * Purpose: holds static variables.
 *
 * @author _phl0w
 * @version 1.0
 * @since 18/04/13
 */
public class Variables {

    public static int primary = -1;
    public static int secondary = -1;
    public static int made = 0;
    public static int startXp = 0;
    public static int startLevel = 0;

    public static long startTime = 0;

    public static Job current = Job.MIX;

    public static Image img = null;

    public static boolean guiDone = false;

    public static final Condition SLEEP_FOR_GUI = new Condition() {
        @Override
        public boolean validate() {
            return Widgets.get(1370, 38).validate();
        }
    };
    public static final Condition SLEEP_FOR_MAKE_SCREEN = new Condition() {
        @Override
        public boolean validate() {
            return Widgets.get(1251, 32).validate();
        }
    };
}
