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

    public static final String RULE_NODE_NAME = "rule";

    public static final String DATA_NODE_NAME = "data";

    /**
     * 库名
     */
    @NonNull
    private String schema;

    /**
     * 表明
     */
    @NonNull
    private String table;

    /**
     * 比较器
     */
    @NonNull
    private Comparator comparator;

    /**
     * 关键列
     */
    private KeyColumn column;
}
