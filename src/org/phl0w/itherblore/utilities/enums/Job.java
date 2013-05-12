package org.phl0w.itherblore.utilities.enums;

import org.phl0w.itherblore.nodes.Antiban;
import org.phl0w.itherblore.nodes.Banking;
import org.phl0w.itherblore.nodes.clean.Clean;
import org.phl0w.itherblore.nodes.mix.Mix;
import org.powerbot.core.script.job.state.Node;

/**
 * iTHerblore 2
 * Job.java
 * Purpose: an enum that holds the different actions w/ their nodes for us.
 *
 * @author _phl0w
 * @version 1.1
 * @since 20/04/2013
 */
public enum Job {

    MIX(new Banking(), new Mix(), new Antiban()),
    CLEAN(new Banking(), new Clean(), new Antiban());

    private Node[] nodes;

    private Job(final Node... nodes) {
        this.nodes = nodes;
    }

    public Node[] getNodes() {
        return nodes;
    }
}
