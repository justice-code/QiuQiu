package org.eddy.sql.method;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by eddy on 2017/3/24.
 */
public class MethodExcludeHandler {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(MethodExcludeHandler.class);

    private Map<String, Method> proxyMethod = new ConcurrentHashMap<>();

    public boolean isExcludeMethod(MappedStatement mappedStatement) {
        String statementId = mappedStatement.getId();
        return false;
    }

    public Method findMethod(String statementId) {
        try {
            Objects.requireNonNull(statementId);
            String strClass = StringUtils.substring(statementId, StringUtils.lastIndexOf(statementId, "."));
        } catch (Exception e) {

        }
        return null;
    }
}
