package org.eddy.xml.rule.impl;

import org.eddy.sql.config.RequestHolder;
import org.eddy.xml.data.DataNode;
import org.eddy.xml.data.RuleNode;
import org.eddy.xml.rule.Comparator;

import java.util.Optional;

/**
 * Created by eddy on 2017/5/3.
 */
public class DefaultComparator extends Comparator {

    @Override
    public DataNode check(RuleNode ruleNode) {
        Object param = RequestHolder.getRequest().getParam();
        return Optional.ofNullable(ruleNode.getDataNodes()).map(nodes -> {
            return nodes.stream().filter(node -> {
                return Optional.ofNullable(param).map(p -> super.script(p, node.getScript())).orElse(false);
            }).findFirst().orElse(null);
        }).orElse(null);
    }
}
