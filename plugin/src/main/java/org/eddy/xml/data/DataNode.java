package org.eddy.xml.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eddy.xml.rule.Comparator;

/**
 * Created by eddy on 2017/5/2.
 */
@Getter
@Setter
@ToString
public class DataNode {

    public static final String ELEMENT_NAME = "rule";

    /**
     * 库名
     */
    private String schema;

    /**
     * 表明
     */
    private String table;

    /**
     * 比较器
     */
    private Comparator comparator;

    /**
     * 关键列
     */
    private KeyColumn column;
}
