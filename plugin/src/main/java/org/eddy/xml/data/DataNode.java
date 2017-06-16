package org.eddy.xml.data;

import lombok.*;
import org.eddy.xml.rule.Comparator;

/**
 * Created by eddy on 2017/5/2.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class DataNode {

    public static final String DATA_NODE_NAME = "data";

    /**
     * 库名
     */
    @NonNull
    private String schema;

    /**
     * 表名
     */
    @NonNull
    private String table;

    /**
     * 对比脚本
     */
    @NonNull
    private String script;

    private RuleNode ruleNode;

}
