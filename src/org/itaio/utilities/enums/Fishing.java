package org.itaio.utilities.enums;

import org.itaio.utilities.Constants;
import org.powerbot.game.api.methods.tab.Inventory;


public enum Fishing {

    CRAYFISH(Constants.CRAYFISH_SPOT, -1, "Cage"),
    TROUT(Constants.TROUT_SPOT, 314, "Lure"),
    LOBSTER(Constants.LOBSTER_SPOT, -1, "Cage");

    private final int npcId;
    private final int baitId;
    private final String interact;

    Fishing(final int npcId, final int baitId, final String interact) {
        this.npcId = npcId;
        this.baitId = baitId;
        this.interact = interact;
    }

    public boolean validate() {
        return baitId == -1 || Inventory.getCount(baitId) > 0;
    }

    public int getNpcId() {
        return npcId;
    }

    public String getInteractingString() {
        return interact;
    }

}
