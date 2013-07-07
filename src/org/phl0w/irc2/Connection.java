package org.phl0w.irc2;

import org.phl0w.irc2.connection.IRC;

public class Connection {

    public static void main(final String... args) {
        final IRC i = new IRC();
        i.run();
    }
}
