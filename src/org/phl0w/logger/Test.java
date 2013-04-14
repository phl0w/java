package org.phl0w.logger;

import org.phl0w.logger.log.Level;

import java.util.Date;

public class Test {

    private static final Logger log = new Logger();

    public static void main(final String... args) {
        log.log("Hello");
        log.log("OMG I AM DEAD", Level.SEVERE);
        log.setLevel(Level.ANTIBAN);
        log.log("adding antiban level log");
        log.dump(Level.ALL, new Date(System.currentTimeMillis() - 1*60*1000));
    }
}
