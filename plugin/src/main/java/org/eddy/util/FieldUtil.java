package org.eddy.util;

import java.lang.reflect.Field;

/**
 * Created by eddy on 2017/6/12.
 */
public class FieldUtil {

    public static void setValue(String fieldName, Class glass, Object target, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = glass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
