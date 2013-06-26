package org.phl0w.aurora.itmaples.utilities;

import ms.aurora.api.methods.Skill;
import ms.aurora.api.util.Timer;

import java.awt.*;

public class Paint {

    private static final Font title = new Font("Monotype Corsiva", Font.PLAIN, 25);
    private static final Font author = new Font("Monotype Corsiva", Font.PLAIN, 16);
    private static final Font info = new Font("Book Antiqua", Font.PLAIN, 15);
    private static final Rectangle bg = new Rectangle(10, 23, 236, 150);
    private static final Rectangle border = new Rectangle(8, 21, 240, 154);

    private static final AlphaComposite ALPHA_COMPOSITE_1 = makeComposite(0.5f);
    private static final AlphaComposite ALPHA_COMPOSITE_2 = makeComposite(1.0f);

    private static AlphaComposite makeComposite(final float alpha) {
        final int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    public static void paint(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.setComposite(ALPHA_COMPOSITE_1);
        g.fill(bg);
        g.setColor(Color.BLACK);
        g.fill(border);
        g.setComposite(ALPHA_COMPOSITE_2);

        g.setColor(Color.WHITE);

        //g.fill(new Rectangle(VirtualMouse.getMouse().getRealX() + 1, VirtualMouse.getMouse().getRealY() - 4, 2, 15));
        //g.fill(new Rectangle(VirtualMouse.getMouse().getRealX() - 6, VirtualMouse.getMouse().getRealY() + 2, 16, 2));

        final Skill woodcutting = Skill.WOODCUTTING;
        final int lvlsGained = woodcutting.getLevel() - Variables.startLevel;
        final int xpGained = woodcutting.getExperience() - Variables.startXp;
        final int xpHour = (int) ((xpGained) * 3600000D / Variables.startTimer.elapsed());
        final int logsHour = (int) ((Variables.chopped) * 3600000D / Variables.startTimer.elapsed());
        g.setFont(title);
        g.drawString("iTMaples", 12, 43);
        g.setFont(author);
        g.drawString("By: _phl0w", 12, 58);

        g.setFont(info);
        g.drawString("Woodcutting: " + woodcutting.getLevel() + " (+" + lvlsGained + ").", 12, 78);
        g.drawString("XP gained: " + xpGained + ". (" + xpHour + "/h)", 12, 93);
        g.drawString("Chopped " + Variables.chopped + " logs (" + logsHour + "/h).", 12, 108);
        g.drawString("TTL: " + getTimeTillLevel(woodcutting, xpGained, xpHour), 12, 123);
        g.drawString("Nests: (not implemented yet).", 12, 138);
        g.drawString("Status: " + Variables.status + ".", 12, 153);
        g.drawString("Time running: " + Timer.formatTime(Variables.startTimer.elapsed()) + ".", 12, 168);
    }

    private static String getTimeTillLevel(Skill skill, int expGained, int expPerHour) {
        int currentLevel = skill.getLevel();
        if (currentLevel == 99) {
            return "n/a";
        }
        int expTillNextLevel = skill.getExperienceToLevel(currentLevel + 1);
        if (expGained > 0) {
            return Timer.formatTime((long) ((double) expTillNextLevel
                    / (double) expPerHour * 3600000));
        }
        return "0:0:0";
    }

}
