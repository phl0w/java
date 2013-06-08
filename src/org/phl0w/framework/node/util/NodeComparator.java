package org.phl0w.framework.node.util;

import org.phl0w.framework.node.impl.PriorityNode;

import java.util.Comparator;

public class NodeComparator implements Comparator<PriorityNode> {

    @Override
    public int compare(final PriorityNode pn1, final PriorityNode pn2) {
        return pn1.priority().compareTo(pn2.priority());
    }
}
