package org.itaio;

import org.itaio.utilities.Paint;
import org.itaio.utilities.enums.State;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.SkillData;

import java.awt.*;

@Manifest(authors = {"_phl0w"}, name = "iTAIO", description = "Does anything you want (sort of), just start near it's location.", version = 1.03)
public class iTAIO extends ActiveScript implements PaintListener {

    public static final State task = State.FISH_LOBSTER;
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
