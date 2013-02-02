package org.itstarter.nodes;

import org.itstarter.utilities.Utilities;
import org.itstarter.utilities.enums.Fishing;
import org.itstarter.utilities.interfaces.Condition;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.Character;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Fish extends Node {

    private NPC fish;
    private Fishing f;

    public Fish(final Fishing f) {
        this.f = f;
    }

    @Override
    public boolean activate() {
        return Players.getLocal().getInteracting() == null && (fish == null ? (fish = NPCs.getNearest(f.getNpcId())) : fish) != null && f.validate() && !Inventory.isFull();
    }

    @Override
    public void execute() {
        if (!fish.isOnScreen()) {
            Camera.turnTo(fish);
            if (!fish.isOnScreen()) {
                Walking.walk(fish);
            }
        }
        if (fish.interact(f.getInteractingString())) {
            Utilities.waitFor(new Condition() {
                @Override
                public boolean activate() {
                    final Character interacting = Players.getLocal().getInteracting();
                    return interacting != null && interacting instanceof NPC;
                }
            }, 2000);
        }
    }
}
