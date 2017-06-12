package org.eddy.sql.interceptor;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

/**
 * Created by eddy on 2017/6/12.
 */
public class BoundSqlSource implements SqlSource {
    private BoundSql boundSql;

    public BoundSqlSource(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return this.boundSql;
    }
}
