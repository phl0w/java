package org.phl0w.itchickens;

import org.phl0w.framework.tree.impl.PriorityTree;
import org.phl0w.itchickens.nodes.Attack;
import org.phl0w.itchickens.nodes.Loot;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;

@Manifest(authors = {"_phl0w"}, name = "iTChickens", version = 1.0d, description = "fucks the chickens up")
public class iTChickens extends PollingScript {

    private final PriorityTree tree = new PriorityTree(new Attack(ctx), new Loot(ctx));

    public iTChickens() {
        getExecQueue(State.START).add(new Runnable() {
            @Override
            public void run() {
                onStart();
            }
        });
        getExecQueue(State.STOP).add(new Runnable() {
            @Override
            public void run() {
                onStop();
            }
        });
    }

    @Override
    public int poll() {
        tree.run();
        return Random.nextInt(70, 100);
    }

    private void onStart() {
        if (!ctx.game.isLoggedIn()) {
            getController().stop();
        }
    }

    private void onStop() {
        log.info("Thx 4 running da script");
    }
}
