package org.phl0w.framework.node;

public abstract class AbstractNode {

    public abstract boolean activate();

    public abstract void execute();

    @Override
    public String toString() {
        return "[".concat(this.getClass().getName().concat("]"));
    }

}
