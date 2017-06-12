package org.eddy.xml.rule;

import org.eddy.xml.data.DataNode;
import org.eddy.xml.data.RuleNode;

import java.util.List;

/**
 * Created by eddy on 2017/4/28.
 */
public interface Comparator {

    DataNode check(RuleNode ruleNode);
}
