package org.phl0w.framework.node;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

public abstract class AbstractNode extends MethodProvider {

    public MethodContext ctx = null;

    public AbstractNode(final MethodContext context) {
        super(context);
        this.ctx = context;
    }

    public abstract boolean activate();

    public abstract void execute();

    @Override
    public String toString() {
        return "[".concat(this.getClass().getName().concat("]"));
    }

}
