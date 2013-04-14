package org.phl0w.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static String getTimestamp(final Date d) {
        return ("[" + SIMPLE_DATE_FORMAT.format(d) + "]");
    }
}
