package org.phl0w.itherblore.utilities;

import org.phl0w.itherblore.utilities.enums.GrimyHerb;
import org.phl0w.itherblore.utilities.enums.Potion;
import org.phl0w.itherblore.utilities.interfaces.Condition;
import org.powerbot.script.util.Delay;
import org.powerbot.script.util.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * iTHerblore 2
 * Methods.java
 * Purpose: provide common methods.
 *
 * @author _phl0w
 * @version 1.2
 * @since 17/4/2013
 */
public class Methods {

    /**
     * Waits for the provided condition to validate, or the time to run out.
     *
     * @param c       - The condition to wait for.
     * @param timeout - How long to wait before timing out (default 2000)
     * @return <tt>true</tt> if the condition validates, <tt>false</tt> if the condition does not validate.
     */
    public static boolean waitFor(final Condition c, final long... timeout) {
        final Timer t = new Timer(timeout.length == 1 ? timeout[0] : 2000);
        while (t.isRunning() && !c.validate()) {
            Delay.sleep(50, 75);
        }
        return c.validate();
    }

    /**
     * Returns the ingredients for a potion's name.
     *
     * @param potion The name of the potion.
     * @return An int[] containing the primary and secondary ingredient of the potion.
     */
    public static int[] getIngredients(String potion) {
        potion = potion.replaceAll((" "), "_").toUpperCase();
        for (final Potion p : Potion.values()) {
            if (p.name().equals(potion)) {
                return new int[]{p.getPrimaryId(), p.getSecondaryId()};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Returns the item id for the grimy herb's name.
     *
     * @param herb The name of the herb.
     * @return The grimy herb item id.
     */
    public static int getHerb(String herb) {
        herb = herb.replaceAll((" "), "_").toUpperCase();
        for (final GrimyHerb h : GrimyHerb.values()) {
            if (h.name().equals(herb)) {
                return h.id();
            }
        }
        return -1;
    }


    /**
     * Gets the image from the URL provided.
     *
     * @param url The URL to get the image from.
     * @return The image.
     */
    public static Image getImage(final String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
