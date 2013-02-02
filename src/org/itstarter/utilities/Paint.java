package org.itstarter.utilities;

import org.itstarter.iTStarter;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.Time;

import java.awt.*;
import java.util.ArrayList;

public class Paint {

    public static void repaint(final Graphics gg) {
        final Graphics2D g = (Graphics2D) gg;
        drawStrings(g);
    }

    private static void drawStrings(Graphics2D g) {
        int Y = 65;
        final int x = 5;
        ArrayList<String> strings = new ArrayList<String>();

        final SkillData temp = iTStarter.sd;
        strings.add("Levels gained: " + temp.level(iTStarter.task.getSkill()));
        strings.add("Experience gained: " + temp.experience(iTStarter.task.getSkill()));
        strings.add("TTL: " + Time.format(temp.timeToLevel(SkillData.Rate.HOUR, iTStarter.task.getSkill())));
        strings.add("Runtime: " + Time.format((System.currentTimeMillis() - iTStarter.startTime)));

        for (final String s : strings) {
            Y += g.getFontMetrics().getHeight();
            g.setColor(Color.black);
            g.drawString(s, x + 1, Y + 1);
            g.setColor(Color.WHITE);
            g.drawString(s, x, Y);
        }
    }
}
