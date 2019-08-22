package org.smart4j.framwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by creasypita on 8/21/2019.
 */
public class ReflectionUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
    public static Object newInstance(Class<?> cls)
    {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("cannot new instance" + cls);
            throw new RuntimeException(e);
        }
    }

    public static Object invokeMethod(Object obj, Method method, Object[] params)
    {
        Object result;
        try {
            result = method.invoke(obj, params);
        } catch (Exception e) {
            LOGGER.error("cannot invokeMethod" + method);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object obj, Field field, Object value)
    {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("cannot setField" + field);
            throw new RuntimeException(e);
        }
    }
}
