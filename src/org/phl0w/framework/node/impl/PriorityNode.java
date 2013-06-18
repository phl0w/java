package org.phl0w.framework.node.impl;

import org.phl0w.framework.node.AbstractNode;
import org.phl0w.framework.node.util.Priority;
import org.powerbot.script.methods.MethodContext;

public abstract class PriorityNode extends AbstractNode {

    public PriorityNode(final MethodContext ctx) {
        super(ctx);
    }

    public abstract Priority priority();

}
