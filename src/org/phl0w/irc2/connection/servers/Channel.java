package org.phl0w.irc2.connection.servers;

public class Channel {

    private String channel, key;

    public Channel(final String channel, final String... key) {
        this.channel = channel;
        if (key.length == 1) {
            this.key = key[0];
        }
    }

    public String getChannel() {
        return channel;
    }

    public String getKey() {
        return key;
    }
}
