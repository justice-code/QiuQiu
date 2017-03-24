package org.eddy.sql.handler;

import org.apache.ibatis.plugin.Invocation;

/**
 * Created by eddy on 2017/3/24.
 */
public class SplitTableHandler {

    public boolean needSplit(Invocation invocation) {
        return false;
    }
}
