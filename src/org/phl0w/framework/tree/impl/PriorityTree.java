package org.phl0w.framework.tree.impl;

import org.phl0w.framework.node.impl.PriorityNode;
import org.phl0w.framework.node.util.NodeComparator;
import org.phl0w.framework.tree.AbstractTree;

import java.util.ArrayList;
import java.util.Collections;

public class PriorityTree extends AbstractTree {

    private ArrayList<PriorityNode> nodes = new ArrayList<>();
    private PriorityNode lastNode = null;

    public PriorityTree(final PriorityNode... pNodes) {
        Collections.addAll(nodes, pNodes);
        sort();
    }

    private void sort() {
        System.out.println("Sorting nodes by priority...");
        Collections.sort(nodes, new NodeComparator());
    }

    private int getNextIndexToExecute() {
        if (lastNode == null) {
            return 0;
        }
        final int lastIndex = nodes.indexOf(lastNode);
        if (lastIndex == nodes.size() - 1) {
            return 0;
        }
        return lastIndex + 1;
    }

    @Override
    public void run() {
        final PriorityNode node = nodes.get(getNextIndexToExecute());
        if (node.activate()) {
            lastNode = node;
            node.execute();
        }
    }


}
