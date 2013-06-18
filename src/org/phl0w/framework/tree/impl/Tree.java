package org.phl0w.framework.tree.impl;

import org.phl0w.framework.node.AbstractNode;
import org.phl0w.framework.tree.AbstractTree;

public class Tree extends AbstractTree {

    private AbstractNode[] aNodes;

    public Tree(final AbstractNode... nodes) {
        aNodes = nodes;
    }

    @Override
    public void run() {
        for (final AbstractNode node : aNodes) {
            if (node.activate()) {
                node.execute();
            }
        }
    }
}
