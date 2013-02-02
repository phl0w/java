package org.itstarter.utilities.enums;

import org.itstarter.utilities.Constants;
import org.powerbot.game.api.methods.tab.Inventory;


public enum Fishing {

    CRAYFISH(Constants.CRAYFISH_SPOT, -1, "Cage"),
    TROUT(Constants.TROUT_SPOT, 314, "Lure");

    private int npcId;
    private int baitId;
    private String interact;

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
