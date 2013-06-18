package org.phl0w.framework;

import org.phl0w.framework.tray.ScriptTray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {

    private static final ScriptTray tray = new ScriptTray("http://www.iconki.com/icons/Places/16x16-Flags-of-the-World/flag_netherlands.png", "test");

    public static void main(final String... args) {
        final PopupMenu menu = tray.getMenu();
        final MenuItem exitItem = new MenuItem("Exit");
        final MenuItem aboutItem = new MenuItem("About");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                tray.remove();
            }
        });
        menu.add(aboutItem);
        menu.addSeparator();
        menu.add(exitItem);
        tray.refreshMenu();
    }
}
