package org.phl0w.irc2.user;

import org.phl0w.irc2.connection.servers.Channel;
import org.phl0w.irc2.connection.servers.Server;

public class Configuration {

    public static final Server RIZON = new Server("irc.rizon.net", 6667, "phl0wbot", new Channel("#aurora-rs"));
    public static final String OWNER = "phl0w";

}
