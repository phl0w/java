package org.phl0w.itherblore.utilities.user;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;

import java.awt.*;

/**
 * iTHerblore 2
 * Paint.java
 * Purpose: render information to our screen.
 *
 * @author _phl0w
 * @version 1.0
 * @since 20/04/2013
 */
public class Paint {

    public static Font font = new Font("Arial", Font.PLAIN, 20);

    public static void onRepaint(final Graphics2D g) {
        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        g.drawImage(Variables.img, 0, 0, null);

        g.setFont(font);
        g.setColor(Color.WHITE);

        g.fill(new Rectangle(Mouse.getX() + 1, Mouse.getY() - 4, 2, 15));
        g.fill(new Rectangle(Mouse.getX() - 6, Mouse.getY() + 2, 16, 2));

        final int currentLevel = Skills.getLevel(Skills.HERBLORE);
        final long timeRun = (System.currentTimeMillis() - Variables.startTime);
        final int expGained = Skills.getExperiences()[Skills.HERBLORE] - Variables.startXp;
        final int expHour = (int) ((expGained) * 3600000D / timeRun);
        final int madeHour = (int) ((Variables.made) * 3600000D / timeRun);

        g.drawString(Time.format(timeRun), 85, 29);
        g.drawString(currentLevel + "(+" + (currentLevel - Variables.startLevel) + ")", 93, 68);
        g.drawString("" + Variables.made, 258, 29);
        g.drawString("" + madeHour, 278, 68);
        g.drawString("" + expGained, 417, 29);
        g.drawString("" + expHour, 439, 68);
    }
}
