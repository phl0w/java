package org.phl0w.itclient2007.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.jar.JarFile;

public class Downloader {

    public JarFile downloadJar() {
        if (requiresUpdate()) {
            try {
                System.out.println("Downloading from: " + Settings.PAGE_URL +
                        WebCrawler.get("gamepack"));
                final ReadableByteChannel rbc = Channels.newChannel(new URL(Settings.PAGE_URL +
                        WebCrawler.get("gamepack")).openStream());
                final FileOutputStream fos = new FileOutputStream(new File(Settings.SAVE_DIRECTORY +
                        WebCrawler.get("gamepack")));
                fos.getChannel().transferFrom(rbc, 0, 1 << 24);
                fos.close();
                return new JarFile(Settings.SAVE_DIRECTORY + WebCrawler.get("gamepack"));
            } catch (Exception e) {
                System.out.println("Could not download jar");
                return null;
            }
        }
        return null;
    }

    private boolean requiresUpdate() {
        final File dir = new File(Settings.SAVE_DIRECTORY);
        if (dir.mkdirs()) {
            return true;
        }
        for (final File f : dir.listFiles()) {
            if (f.getName().equals(WebCrawler.get("gamepack"))) {
                return false;
            }
        }
        return true;
    }
}
