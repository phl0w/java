package org.phl0w.irc2.utilities;

import org.phl0w.irc2.connection.IRC;
import org.phl0w.irc2.connection.servers.Channel;

public class MSL {


    public MSL() {

    }

    public void setNick(final String newNick) {
        IRC.sendRaw("NICK ".concat(newNick));
    }

    public void define(final String user, final String host, final String server, final String real) {
        IRC.sendRaw("USER ".concat(user).concat(" ").concat(host).concat(" ").concat(server).concat(" ").concat(real));
    }

    public void join(final String channel, final String... key) {
        if (key.length == 1) {
            IRC.sendRaw("JOIN ".concat(channel).concat(" ").concat(key[0]));
        } else {
            IRC.sendRaw("JOIN ".concat(channel));
        }
    }

    public void join(final Channel[] channels) {
        for (final Channel c : channels) {
            if (c.getKey() != null) {
                join(c.getChannel(), c.getKey());
            } else {
                join(c.getChannel());
            }
        }
    }
}
