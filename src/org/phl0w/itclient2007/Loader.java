package org.phl0w.itclient2007;

import org.phl0w.itclient2007.asm.updater.Updater;
import org.phl0w.itclient2007.ui.UIFrame;
import org.phl0w.itclient2007.utilities.*;

import java.applet.Applet;
import java.net.URL;
import java.net.URLClassLoader;

public class Loader {

    private static final Updater updater = new Updater();

    public static void main(final String[] args) {
        final World random = Servers.getRandom();
        if (random != null) {
            Settings.setPageUrl(random.getSite());
        }
        final Downloader d = new Downloader();
        new Extractor().extractZip(d.downloadJar());
        final URLClassLoader u = getClassLoader();
        updater.update();
        try {
            final Class<?> clazz = Class.forName("client", true, u);
            final Class<? extends Applet> appClazz = clazz.asSubclass(Applet.class);
            final Applet app = appClazz.newInstance();
            final UIFrame frame = new UIFrame(app);
            frame.setVisible(true);
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static URLClassLoader getClassLoader() {
        try {
            return URLClassLoader.newInstance(new URL[]{new URL("file:" + Settings.SAVE_DIRECTORY + "gamepack/")});
        } catch (final Exception e) {
            return null;
        }
    }

}
