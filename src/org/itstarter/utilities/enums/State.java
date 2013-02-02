package org.itstarter.utilities.enums;


import org.itstarter.nodes.Antiban;
import org.itstarter.nodes.Drop;
import org.itstarter.nodes.Fish;
import org.itstarter.nodes.Mine;
import org.itstarter.utilities.Constants;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Skills;

import java.util.ArrayList;
import java.util.Collections;

public enum State {
    MINE(Skills.MINING, new Mine(), new Drop(Constants.COPPER_ORE)),
    FISH_CRAYFISH(Skills.FISHING, new Fish(Fishing.CRAYFISH), new Drop(Constants.CRAYFISH)),
    FISH_TROUT_AND_SALMON(Skills.FISHING, new Fish(Fishing.TROUT), new Drop(Constants.TROUT, Constants.SALMON));

    private Node[] nodes;
    private int skill;

    State(final int skill, final Node... nodes) {
        this.nodes = addAll(nodes);
        this.skill = skill;
    }

    public Node[] get() {
        return nodes;
    }

    public int getSkill() {
        return skill;
    }

    public Node[] addAll(final Node... nodes) {
        final ArrayList<Node> nodez = new ArrayList<Node>();
        Collections.addAll(nodez, nodes);
        nodez.add(new Antiban());
        final Node[] nodes1 = new Node[nodez.size()];
        return nodez.toArray(nodes1);
    }


}
