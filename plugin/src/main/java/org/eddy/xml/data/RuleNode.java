package org.eddy.xml.data;

import lombok.*;
import org.eddy.xml.rule.Comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eddy on 2017/6/8.
 */
@Getter
@Setter
@ToString(exclude = "dataNodes")
@NoArgsConstructor
@RequiredArgsConstructor
public class RuleNode {

    public static final String RULE_NODE_NAME = "rule";

    @NonNull
    private KeyColumn keyColumn;

    private List<DataNode> dataNodes = new ArrayList<>();

    /**
     * 比较器
     */
    @NonNull
    private Comparator comparator;

    public String sourceTable() {
        return keyColumn.getTable();
    }

    public void add(DataNode dataNode) {
        dataNodes.add(dataNode);
    }
}
