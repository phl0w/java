package org.phl0w.projectdestruction;

import org.phl0w.projectdestruction.bot.Updater;
import org.phl0w.projectdestruction.bot.interfaces.ClientHook;

import java.applet.Applet;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.jar.JarFile;

public class Main {

    public static final HashMap<String, String> mappedHooks = new HashMap<>();

    public static void main(final String... args) {
        try {
            final Updater u = new Updater(new JarFile("C:\\Users\\Niels\\Desktop\\diversity.jar"));
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            final Class<?> clazz = getClassLoader().loadClass("ClientHook");
            final Class<? extends Applet> appClazz = clazz.asSubclass(Applet.class);
            final Applet app = appClazz.newInstance();
            app.setVisible(true);
            app.init();
            app.start();

            final ClientHook c = (ClientHook) clazz.newInstance();

            while (true) {
                final Scanner in = new Scanner(System.in);
                final String nextL = in.nextLine();
                if (nextL.contains("break")) {
                    break;
                } else {
                    if (nextL.contains("cmd")) {
                       System.out.println(c.getServer());

                    }
                }
            }
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static URLClassLoader getClassLoader() {
        try {
            return URLClassLoader.newInstance(new URL[]{new URL("jar:file:C:\\Users\\Niels\\Desktop\\diversity_DUMP.jar!/")});
        } catch (final Exception e) {
            return null;
        }
    }

    public static void mapHook(String s1, String s2) {
        mappedHooks.put(s1, s2);
    }

    public static String getMappedHook(String s1) {
        return mappedHooks.get(s1);
    }
}
