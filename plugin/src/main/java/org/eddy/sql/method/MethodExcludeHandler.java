package org.eddy.sql.method;

import org.apache.ibatis.plugin.Invocation;

/**
 * Created by eddy on 2017/3/24.
 */
public class MethodExcludeHandler {

    public boolean isExcludeMethod(Invocation invocation) {
        return false;
    }
}
