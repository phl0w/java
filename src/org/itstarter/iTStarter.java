package org.itstarter;

import org.itstarter.utilities.Paint;
import org.itstarter.utilities.enums.State;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.SkillData;

import java.awt.*;

@Manifest(authors = {"_phl0w"}, name = "Starter script", description = "start near the lumbridge lodestone", version = 1.01)
public class iTStarter extends ActiveScript implements PaintListener {

    public static final State task = State.FISH_TROUT_AND_SALMON;
    private static Tree jobs = null;

    public static SkillData sd = null;
    public static long startTime;

    @Override
    public void onStart() {
        if (sd == null) {
            sd = new SkillData();
        }

        if (jobs == null) {
            jobs = new Tree(task.get());
        }

        startTime = System.currentTimeMillis();
    }

    @Override
    public int loop() {
        if (Game.isLoggedIn()) {
            if (jobs != null) {
                final Node job = jobs.state();
                if (job != null) {
                    jobs.set(job);
                    getContainer().submit(job);
                    job.join();
                }
            }
        }
        return Random.nextInt(20, 50);
    }

    @Override
    public void onRepaint(final Graphics gg) {
        if (sd != null) {
            Paint.repaint(gg);
        }
    }

}