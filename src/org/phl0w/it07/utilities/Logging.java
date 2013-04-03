package org.phl0w.it07.utilities;

public class Logging {

    public static void log(final String msg, final Class<?> clazz) {
        System.out.println("[" + clazz.getName() + "]: " + msg);
    }

}
