package org.itaio.utilities.enums;


import org.itaio.nodes.Antiban;
import org.itaio.nodes.Drop;
import org.itaio.nodes.Walker;
import org.itaio.nodes.fishing.Fish;
import org.itaio.nodes.fishing.Stiles;
import org.itaio.nodes.mining.Mine;
import org.itaio.utilities.Constants;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Skills;

import java.util.ArrayList;
import java.util.Collections;

public enum State {
    MINE(Skills.MINING, new Mine(), new Drop(Constants.COPPER_ORE)),
    FISH_CRAYFISH(Skills.FISHING, new Fish(Fishing.CRAYFISH), new Drop(Constants.CRAYFISH)),
    FISH_TROUT_AND_SALMON(Skills.FISHING, new Fish(Fishing.TROUT), new Drop(Constants.TROUT, Constants.SALMON)),
    FISH_LOBSTER(Skills.FISHING, new Fish(Fishing.LOBSTER), new Walker(Constants.PATH_TO_STILES, Constants.LOBSTER_CONDITION_1), new Walker(Constants.PATH_TO_LOBSTERS, Constants.LOBSTER_CONDITION_2), new Stiles());

    private final Node[] nodes;
    private final int skill;

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
