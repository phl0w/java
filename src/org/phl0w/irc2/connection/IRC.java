package org.phl0w.irc2.connection;

import org.phl0w.irc2.connection.servers.Server;
import org.phl0w.irc2.user.Configuration;
import org.phl0w.irc2.user.Debug;
import org.phl0w.irc2.utilities.MSL;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class IRC extends Thread {

    private static Socket socket = null;
    private static Server server = Configuration.RIZON;
    private static String line;
    public static ArrayList<String> permission = new ArrayList<>();
    private static final MSL m = new MSL();

    @Override
    public void run() {
        try {
            this.init();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        Debug.log("Establishing connection with " + server.toString() + ".");
        socket = new Socket(server.getServer(), server.getPort());
        Debug.log("Successfully connecting to IRC...");
        socket.setTcpNoDelay(true);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        m.setNick(server.getNickname());

        String hostname = "";
        try {
            final InetAddress inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
        } catch (final UnknownHostException uhe) {
            uhe.printStackTrace();
        }
        m.define(server.getNickname(), hostname, server.getServer(), server.getNickname());
        m.join(server.getChannels());
        try {
            while (true) {
                if ((line = reader.readLine()) != null && accept(line)) {

                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendRaw(String line) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(line.concat(System.getProperty("line.separator")));
            Debug.log("-> " + line);
            writer.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static boolean accept(final String line) {
        if (line.contains("PING") || line.contains("phl0w") || line.startsWith(":irc.")) {
            return true;
        } else {
            final String name = line.split("!")[0].replaceAll(":", "");
            if (!name.equals("")) {
                if (name.equals(Configuration.OWNER) || permission.contains(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}
