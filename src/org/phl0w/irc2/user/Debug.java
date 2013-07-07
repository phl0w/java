package org.phl0w.irc2.user;

import java.util.logging.Logger;

public class Debug {

    public static final Logger l = Logger.getLogger("Debug");

    public static void log(final String message) {
        l.info(message);
    }
}
