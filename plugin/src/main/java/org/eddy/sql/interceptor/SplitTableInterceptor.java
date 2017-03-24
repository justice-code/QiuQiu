package org.eddy.sql.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by eddy on 2017/3/24.
 */
@Intercepts({@Signature(type=StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SplitTableInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SplitTableInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
