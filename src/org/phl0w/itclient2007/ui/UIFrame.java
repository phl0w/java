package org.phl0w.itclient2007.ui;

import org.phl0w.itclient2007.utilities.Settings;
import org.phl0w.itclient2007.utilities.WebCrawler;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;

public class UIFrame extends JFrame implements AppletStub {

    private static final HashMap<String, String> parameters = WebCrawler.crawl();

    public UIFrame(final Applet loader) {
        this.setTitle("iTClient 2007 - v" + Settings.VERSION);
        this.setResizable(false);
        loader.setStub(this);
        loader.init();
        loader.start();
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        JPanel theGame = new JPanel(new BorderLayout());
        theGame.setPreferredSize(new Dimension(768, 560));
        setPreferredSize(new Dimension(768, 658));
        theGame.add(loader);
        getContentPane().add(loader, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(769, 531);
    }

    @Override
    public final URL getDocumentBase() {
        try {
            return new URL(Settings.PAGE_URL);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public final URL getCodeBase() {
        try {
            return new URL(Settings.PAGE_URL);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public final String getParameter(final String name) {
        return parameters.get(name);
    }

    @Override
    public final AppletContext getAppletContext() {
        return null;
    }

    @Override
    public void appletResize(final int width, final int height) {

    }
}
