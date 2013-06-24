package org.phl0w.itherblore;

import org.phl0w.framework.tree.impl.PriorityTree;
import org.phl0w.itherblore.nodes.Antiban;
import org.phl0w.itherblore.nodes.Banking;
import org.phl0w.itherblore.nodes.mix.Mix;
import org.phl0w.itherblore.utilities.GUI;
import org.phl0w.itherblore.utilities.Methods;
import org.phl0w.itherblore.utilities.Variables;
import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;

import javax.swing.*;
import java.awt.*;

/**
 * iTHerblore 2
 * iTHerblore.java
 * Purpose: main class of iTHerblore script.
 *
 * @author _phl0w
 * @version 2.1
 * @since 17/4/2013
 */
@Manifest(authors = {"_phl0w"}, name = "iTHerblore", version = 2.01, description = "Mixes potions & cleans herbs. Rewritten from scratch!", website = "http://www.powerbot.org/community/topic/674277-itherblore-makes-your-potions-unf-finished-cleans-your-herbs-scroll-of-cleansing-support/")
public class iTHerblore extends PollingScript implements PaintListener, MessageListener {

    private static PriorityTree tree;
    private static final Font FONT = new Font("Arial", Font.PLAIN, 20);
    final GUI g = new GUI(this);

    public iTHerblore() {
        getExecQueue(State.START).add(new Runnable() {
            @Override
            public void run() {
                if (ctx.game.isLoggedIn()) {
                    Variables.img = Methods.getImage("http://i.imgur.com/gUp0Hyn.png");
                    Variables.startLevel = ctx.skills.getLevel(Skills.HERBLORE);
                    Variables.startXp = ctx.skills.getExperience(Skills.HERBLORE);
                    Variables.startTime = System.currentTimeMillis();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            g.setVisible(true);
                        }
                    });
                } else {
                    log.info("Please start this script while logged in.");
                    getController().stop();
                }
            }
        });
    }

    @Override
    public int poll() {
        if (tree != null) {
            tree.run();
        }
        return Random.nextInt(150, 230);
    }


    @Override
    public void repaint(final Graphics g1) {
        final Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        g.drawImage(Variables.img, 0, 0, null);

        g.setFont(FONT);
        g.setColor(Color.WHITE);

        g.fill(new Rectangle((int) ctx.mouse.getLocation().getX() + 1, (int) ctx.mouse.getLocation().getY() - 4, 2, 15));
        g.fill(new Rectangle((int) ctx.mouse.getLocation().getX() - 6, (int) ctx.mouse.getLocation().getY() + 2, 16, 2));

        final int currentLevel = ctx.skills.getLevel(Skills.HERBLORE);
        final long timeRun = (System.currentTimeMillis() - Variables.startTime);
        final int expGained = ctx.skills.getExperience(Skills.HERBLORE) - Variables.startXp;
        final int expHour = (int) ((expGained) * 3600000D / timeRun);
        final int madeHour = (int) ((Variables.made) * 3600000D / timeRun);

        g.drawString(Timer.format(timeRun), 85, 29);
        g.drawString(currentLevel + "(+" + (currentLevel - Variables.startLevel) + ")", 93, 68);
        g.drawString("" + Variables.made, 258, 29);
        g.drawString("" + madeHour, 278, 68);
        g.drawString("" + expGained, 417, 29);
        g.drawString("" + expHour, 439, 68);
    }

    @Override
    public void messaged(final MessageEvent me) {
        final String msg = me.getMessage();
        if (msg.contains("you put the") || msg.contains("into the vial") || msg.contains("mix the") || msg.contains("serum 207") || msg.contains("clean the dirt")) {
            Variables.made++;
        }
    }

    public void setTree(final boolean isSelected, final String selected) {
        if (isSelected) {
            tree = new PriorityTree(new Banking(ctx), new Mix(ctx), new Antiban(ctx));
            final int[] ingredients = Methods.getIngredients(selected);
            Variables.primary = ingredients[0];
            Variables.secondary = ingredients[1];
            System.out.println("prim: " + Variables.primary + ", secon: " + Variables.secondary);
        } else {
            tree = new PriorityTree(new Banking(ctx), new Mix(ctx), new Antiban(ctx));
            Variables.primary = Methods.getHerb(selected);
            System.out.println("prim: " + Variables.primary);
        }

    }
}
