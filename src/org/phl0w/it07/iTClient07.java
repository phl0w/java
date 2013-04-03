package org.phl0w.it07;

import org.phl0w.it07.loader.Loader;
import org.phl0w.it07.loader.UIFrame;
import org.phl0w.it07.methods.Download;
import org.phl0w.it07.methods.Servers;
import org.phl0w.it07.methods.Version;
import org.phl0w.it07.methods.Worlds;
import org.phl0w.it07.utilities.Constants;
import org.phl0w.it07.utilities.World;

import java.applet.Applet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class iTClient07 {

    private static final Random r = new Random(System.currentTimeMillis());
    private static final ArrayList<World> worlds = Servers.parseWorlds();
    private static final boolean checkUpdates = false;

    public static void main(final String... args) {
        final World random = worlds.get(r.nextInt(worlds.size()));
        System.out.println("Random world of today: " + random.toString());
        final String jar = Worlds.retrieveJarLocation(random);
        System.out.println("Gamepack detected @ " + jar);
        final long start = System.currentTimeMillis();
        if (Download.download(Constants.JAR_LOCATION, jar)) {
            System.out.println("Successfully downloaded the jar to " + Constants.JAR_LOCATION.getPath() + " (" +
                    (System.currentTimeMillis() - start) + " ms).");
            final Loader l = new Loader(Constants.JAR_PATH);
            l.loadClasses();
            try {
                final Class<? extends Applet> client = l.classes.get("client").asSubclass(Applet.class);
                final Applet app = client.newInstance();
                final UIFrame ui = new UIFrame(app, random.getSite());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (checkUpdates) {
            try {
                System.out.println("Web Version: " + Version.getCurrentRevision(random.trim()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
