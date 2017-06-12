package org.eddy.sql.config;

import org.springframework.core.NamedThreadLocal;

/**
 * Created by eddy on 2017/6/12.
 */
public class RequestHolder {

    private static ThreadLocal<SplitRequest> requestHolder = new NamedThreadLocal<>("sqlSplitRequest");

    public static void resetRequest() {
        requestHolder.remove();
    }

    public static void setRequestAttributes(Object param, String ognl) {
        requestHolder.set(new SplitRequest(param, ognl));
    }

    public static SplitRequest getRequest() {
        return requestHolder.get();
    }
}