package org.eddy.xml.rule.impl;

import org.apache.commons.collections.CollectionUtils;
import org.eddy.xml.data.DataNode;
import org.eddy.xml.data.RuleNode;
import org.eddy.xml.rule.Comparator;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by eddy on 2017/5/3.
 */
public class DefaultComparator implements Comparator {

    @Override
    public DataNode check(RuleNode ruleNode, Object param) {
        return Optional.ofNullable(ruleNode.getDataNodes()).map(nodes -> {
            if (CollectionUtils.isNotEmpty(nodes)) {
                return nodes.get(0);
            } else {
                return null;
            }
        }).orElse(null);
    }
}
