package org.eddy.sql.handler;

import org.apache.ibatis.mapping.MappedStatement;

/**
 * Created by eddy on 2017/3/24.
 */
public class SplitTableHandler {

    public boolean needSplit(MappedStatement mappedStatement) {
        return false;
    }
}
