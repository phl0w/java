package org.phl0w.framework.tree.impl;

import org.phl0w.framework.node.AbstractNode;
import org.phl0w.framework.tree.AbstractTree;

import java.util.ArrayList;
import java.util.Collections;

public class Tree extends AbstractTree {

    private ArrayList<AbstractNode> aNodes = new ArrayList<>();

    public Tree(final AbstractNode... nodes) {
        Collections.addAll(aNodes, nodes);
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
