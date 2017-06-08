package org.eddy.util;

import net.sf.jsqlparser.schema.Table;
import org.apache.commons.lang3.StringUtils;
import org.eddy.xml.context.XmlDataContext;
import org.eddy.xml.data.RuleNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by eddy on 2017/6/8.
 */
public class TableList {

    private List<String> tables = new ArrayList<>();

    public void add(Table table, Supplier holder) {
        Objects.requireNonNull(holder);
        tables.add(holder.getName(table));
        replace(table);
    }

    private void replace(Table table) {
        XmlDataContext.getContext().getNodes().forEach(ruleNode -> {
            if (! checkTableName(table, ruleNode)) {
                return;
            }
            Optional.ofNullable(ruleNode.getComparator().check(ruleNode)).ifPresent(node -> {
                table.setName(node.getTable());
                table.setSchemaName(node.getSchema());
            });
        });
    }

    /**
     * 表名对比方法
     * @param table
     * @param ruleNode
     * @return true：对比通过，需要替换表名
     */
    private boolean checkTableName(Table table, RuleNode ruleNode) {
        return StringUtils.equalsIgnoreCase(table.getName(), ruleNode.sourceTable());
    }

    public boolean contains(String tableName) {
//        return tables.contains(tableName);
        return false;
    }

    public List<String> result() {
        return tables;
    }

}
