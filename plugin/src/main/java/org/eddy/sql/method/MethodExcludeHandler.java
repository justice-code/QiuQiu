package org.eddy.sql.method;

import org.apache.ibatis.mapping.MappedStatement;

/**
 * Created by eddy on 2017/3/24.
 */
public class MethodExcludeHandler {

    public boolean isExcludeMethod(MappedStatement mappedStatement) {
        return false;
    }
}
