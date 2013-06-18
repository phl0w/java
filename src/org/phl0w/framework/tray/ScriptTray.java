package org.phl0w.framework.tray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class ScriptTray {

    private final Logger log = Logger.getLogger("ScriptTray");

    private PopupMenu menu;
    private TrayIcon icon;
    private SystemTray tray;

    public ScriptTray(final String linkToImage, final String... tooltip) {
        create(getImage(linkToImage), tooltip[0]);
    }

    private void create(final Image img, final String tooltip) {
        if (!SystemTray.isSupported()) {
            log.info("System tray is not supported on this computer.");
        } else if (img == null) {
            log.info("Image can not be null.");
        } else {
            tray = SystemTray.getSystemTray();
            menu = new PopupMenu();
            icon = new TrayIcon(img);
            icon.setToolTip(tooltip);
            log.info("Successfully created menu.");
        }
    }

    private Image getImage(final String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public PopupMenu getMenu() {
        return menu;
    }

    public void refreshMenu() {
        icon.setPopupMenu(menu);
        try {
            tray.add(icon);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        tray.remove(icon);
    }
}
