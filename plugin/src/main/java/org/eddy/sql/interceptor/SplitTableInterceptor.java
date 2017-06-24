package org.eddy.sql.interceptor;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.eddy.sql.config.RequestHolder;
import org.eddy.sql.config.SplitRequest;
import org.eddy.sql.split.ReplaceTablesNamesFinder;
import org.eddy.util.FieldUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by eddy on 2017/3/24.
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class SplitTableInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SplitTableInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Optional.ofNullable(RequestHolder.getRequest()).ifPresent(splitRequest -> genSql(invocation, splitRequest));
        return invocation.proceed();
    }

    private void genParams(Invocation invocation, SplitRequest splitRequest) throws OgnlException {
        Object[] params = new Object[splitRequest.getOgnl().length];
        for (int i = 0; i < splitRequest.getOgnl().length; i++) {
            params[i] = Ognl.getValue(splitRequest.getOgnl()[i], invocation.getArgs()[1]);
        }
        splitRequest.setParam(params);
    }

    private void genSql(Invocation invocation, SplitRequest splitRequest) {
        try {
            genParams(invocation, splitRequest);

            BoundSql boundSql = ((MappedStatement)invocation.getArgs()[0]).getBoundSql(invocation.getArgs()[1]);
            Statement statement = CCJSqlParserUtil.parse(boundSql.getSql());
            String sql = parseSql(statement);

            FieldUtil.setValue("sql", BoundSql.class, boundSql, sql);
            MappedStatement mappedStatement = copyMappedStatement((MappedStatement)invocation.getArgs()[0], boundSql);
            invocation.getArgs()[0] = mappedStatement;

        } catch (OgnlException ognl) {
            logger.error("ognl exception", ognl);
        } catch (JSQLParserException e) {
            logger.error("jSqlParser exception", e);
        } catch (Exception e1) {
            logger.error("error happend", e1);
        }
    }

    private String parseSql(Statement statement) {
        Objects.requireNonNull(statement);

        if (statement instanceof Select) {
            ReplaceTablesNamesFinder finder = new ReplaceTablesNamesFinder();
            finder.getTableList((Select) statement);
        } else if (statement instanceof Update) {
            ReplaceTablesNamesFinder finder = new ReplaceTablesNamesFinder();
            finder.getTableList((Update) statement);
        } else if (statement instanceof Insert) {
            ReplaceTablesNamesFinder finder = new ReplaceTablesNamesFinder();
            finder.getTableList((Insert) statement);
        } else if (statement instanceof Delete) {
            ReplaceTablesNamesFinder finder = new ReplaceTablesNamesFinder();
            finder.getTableList((Delete) statement);
        }

        return statement.toString();

    }

    private MappedStatement copyMappedStatement(MappedStatement ms, BoundSql boundSql) throws NoSuchFieldException, IllegalAccessException {
        return copyMappedStatement(ms, new BoundSqlSource(boundSql));
    }

    private MappedStatement copyMappedStatement(MappedStatement ms, SqlSource sqlSource) throws NoSuchFieldException, IllegalAccessException {
        MappedStatement nms;
        nms = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), sqlSource,
                ms.getSqlCommandType()).cache(ms.getCache()).databaseId(ms.getDatabaseId())
                .fetchSize(ms.getFetchSize()).flushCacheRequired(true).keyGenerator(ms.getKeyGenerator())
                .parameterMap(ms.getParameterMap()).resource(ms.getResource())
                .resultMaps(ms.getResultMaps()).resultSetType(ms.getResultSetType())
                .statementType(ms.getStatementType()).timeout(ms.getTimeout()).useCache(ms.isUseCache())
                .build();
        FieldUtil.setValue("keyColumns", nms.getClass(), nms, ms.getKeyColumns());
        FieldUtil.setValue("keyProperties", nms.getClass(), nms, ms.getKeyProperties());
        return nms;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
