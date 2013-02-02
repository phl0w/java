package org.itaio.nodes;

import org.itaio.utilities.interfaces.Condition;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.wrappers.map.Path;
import org.powerbot.game.api.wrappers.map.TilePath;

import java.util.EnumSet;

public class Walker extends Node {

    private final TilePath path;
    private final Condition con;

    public Walker(final TilePath path, final Condition condition) {
        this.path = path;
        this.con = condition;
    }

    @Override
    public boolean activate() {
        return con.activate();
    }

    @Override
    public void execute() {
        path.traverse((EnumSet.of(Path.TraversalOption.HANDLE_RUN, Path.TraversalOption.SPACE_ACTIONS)));
    }

}
