package org.phl0w.itherblore;

import org.phl0w.itherblore.utilities.GUI;
import org.phl0w.itherblore.utilities.Methods;
import org.phl0w.itherblore.utilities.enums.Job;
import org.phl0w.itherblore.utilities.user.Paint;
import org.phl0w.itherblore.utilities.user.Variables;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.core.script.methods.Game;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;

import javax.swing.*;
import java.awt.*;

/**
 * iTHerblore 2
 * iTHerblore.java
 * Purpose: main class of iTHerblore script.
 *
 * @author _phl0w
 * @version 2.0
 * @since 17/4/2013
 */
@Manifest(authors = {"_phl0w"}, name = "iTHerblore", version = 2.01, description = "Mixes potions & cleans herbs. Rewritten from scratch!", website = "http://www.powerbot.org/community/topic/674277-itherblore-makes-your-potions-unf-finished-cleans-your-herbs-scroll-of-cleansing-support/")
public class iTHerblore extends ActiveScript implements PaintListener, MessageListener {

    private static Tree tree = null;

    @Override
    public int loop() {
        if (!Game.isLoggedIn()) {
            stop();
        } else {
            if (Variables.guiDone) {
                final Node job = tree.state();
                if (job != null) {
                    getContainer().submit(job);
                    tree.set(job);
                    job.join();
                }
            }
        }
        return Random.nextInt(150, 230);
    }

    @Override
    public void onStart() {
        if (Game.isLoggedIn()) {
            Variables.img = Methods.getImage("http://i.imgur.com/gUp0Hyn.png");
            Variables.startLevel = Skills.getLevel(Skills.HERBLORE);
            Variables.startXp = Skills.getExperience(Skills.HERBLORE);
            Variables.startTime = System.currentTimeMillis();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    final GUI g = new GUI();
                    g.setVisible(true);
                }
            });
        } else {
            log.info("Please start this script while logged in.");
            stop();
        }
    }

    @Override
    public void onRepaint(final Graphics g1) {
        Paint.onRepaint((Graphics2D) g1);
    }

    @Override
    public void messageReceived(final MessageEvent me) {
        final String msg = me.getMessage();
        if (msg.contains("you put the") || msg.contains("into the vial") || msg.contains("mix the") || msg.contains("serum 207") || msg.contains("clean the dirt")) {
            Variables.made++;
        }
    }

    public static void setTree(final Job jobs) {
        tree = new Tree(jobs.getNodes());
    }


}
