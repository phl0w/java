package org.phl0w.irc2.connection.servers;

public class Server {

    private String hostname, nickname;
    private int port;
    private Channel[] channels;

    public Server(final String hostname, final int port, final String nickname, final Channel... channels) {
        this.hostname = hostname;
        this.port = port;
        this.nickname = nickname;
        this.channels = channels;
    }

    public String getServer() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getNickname() {
        return nickname;
    }

    public Channel[] getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        return getServer().concat(":").concat(Integer.toString(getPort()));
    }
}
