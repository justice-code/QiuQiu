package org.eddy.sql.method;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.eddy.sql.method.annotation.Exclude;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by eddy on 2017/3/24.
 */
public class MethodExcludeHandler {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(MethodExcludeHandler.class);

    private Map<String, Method> proxyMethod = new ConcurrentHashMap<>();

    /**
     * 判断mapper方法是否直接执行
     * @param mappedStatement
     * @return true: 该mapper方法直接执行
     */
    public boolean isExcludeMethod(MappedStatement mappedStatement) {
        String statementId = mappedStatement.getId();
        Method mappedMethod = findMethod(statementId);
        return mappedMethod.isAnnotationPresent(Exclude.class);
    }

    public Method findMethod(String statementId) {
        try {
            Objects.requireNonNull(statementId);
            if (proxyMethod.containsKey(statementId)) {
                return proxyMethod.get(statementId);
            }
            String strClass = StringUtils.substring(statementId, 0, StringUtils.lastIndexOf(statementId, "."));
            String strMethod = StringUtils.substring(statementId, StringUtils.lastIndexOf(statementId, ".") + 1);
            Class mapper = Class.forName(strClass);
            return Arrays.stream(mapper.getDeclaredMethods()).filter(method -> StringUtils.equals(method.getName(), strMethod)).findFirst().map(method -> {
                proxyMethod.put(statementId, method);
                return method;
            }).orElse(null);
        } catch (ClassNotFoundException e) {
            logger.error("find class error, statementId: " + statementId, e);
        } catch (Exception e) {
            logger.error("case error, statementId: " + statementId, e);
        }
        return null;
    }
}
