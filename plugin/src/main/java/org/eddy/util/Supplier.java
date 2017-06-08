package org.eddy.util;

import net.sf.jsqlparser.schema.Table;

/**
 * Created by eddy on 2017/6/8.
 */
@FunctionalInterface
public interface Supplier {

    String getName(Table table);
}
