package org.phl0w.it07.loader;

import org.phl0w.it07.methods.Crawler;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;

public class UIFrame extends JFrame implements AppletStub {

    private Applet app;
    private static String url;
    private static HashMap<String, String> parameters = new HashMap<String, String>();

    public UIFrame(final Applet loader, final String url) {
        this.setTitle("iTClient07 - by _phl0w");
        this.setResizable(false);
        this.url = url;
        final Crawler c = new Crawler(url);
        parameters = c.crawl();
        loader.setStub(this);
        loader.init();
        loader.start();
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        JPanel theGame = new JPanel(new BorderLayout());
        theGame.setPreferredSize(new Dimension(768, 560));
        setPreferredSize(new Dimension(768, 658));
        theGame.add(loader);
        getContentPane().add(loader, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(769, 531);
    }

    public void addApplet(Applet a) {
    }

    @Override
    public final URL getDocumentBase() {
        try {
            return new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public final URL getCodeBase() {
        try {
            return new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public final String getParameter(String name) {
        return parameters.get(name);
    }

    @Override
    public final AppletContext getAppletContext() {
        return null;
    }

    @Override
    public void appletResize(int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
