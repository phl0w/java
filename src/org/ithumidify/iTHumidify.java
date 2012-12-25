package org.ithumidify;

import org.ithumidify.nodes.Antiban;
import org.ithumidify.nodes.Banking;
import org.ithumidify.nodes.Humidify;
import org.ithumidify.nodes.OpenAbilities;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.util.Random;

@Manifest(authors = {"_phl0w"}, name = "iTHumidify", version = 1.0d, description = "Turns clay into soft clay for you. Put humidify on the first slot of your abilities. Have a steam staff equipped. Have astral runes in inventory.")
public class iTHumidify extends ActiveScript {

    private static final Tree jobs = new Tree(new Node[]{new OpenAbilities(), new Humidify(), new Banking(), new Antiban()});

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
        return Random.nextInt(200, 300);
    }
}
