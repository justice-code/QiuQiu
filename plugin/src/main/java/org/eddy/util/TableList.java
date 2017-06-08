package org.eddy.util;

import net.sf.jsqlparser.schema.Table;
import org.apache.commons.lang3.StringUtils;
import org.eddy.xml.context.XmlDataContext;

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
            if (! StringUtils.equalsIgnoreCase(table.getName(), ruleNode.sourceTable())) {
                return;
            }
            Optional.ofNullable(ruleNode.getComparator().check(ruleNode)).ifPresent(node -> {
                table.setName(node.getTable());
                table.setSchemaName(node.getSchema());
            });
        });
    }

    public boolean contains(String tableName) {
//        return tables.contains(tableName);
        return false;
    }

    public List<String> result() {
        return tables;
    }

}
