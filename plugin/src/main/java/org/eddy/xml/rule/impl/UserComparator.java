package org.eddy.xml.rule.impl;

import org.eddy.sql.config.RequestHolder;
import org.eddy.xml.data.DataNode;
import org.eddy.xml.data.RuleNode;
import org.eddy.xml.rule.Comparator;

import java.util.Optional;

/**
 * Created by eddy on 2017/6/16.
 */
public class UserComparator extends Comparator{

    @Override
    public DataNode check(RuleNode ruleNode) {
        Object[] param = RequestHolder.getRequest().getParam();
        String mapper = RequestHolder.getRequest().getMapper();
        return Optional.ofNullable(ruleNode.getDataNodes()).map(nodes -> {
            return nodes.stream().filter(node -> {
                return Optional.ofNullable(param).map(p -> super.script(mapper, p, node.getScript())).orElse(false);
            }).findFirst().orElse(null);
        }).orElse(null);
    }

}
