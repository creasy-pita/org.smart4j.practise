package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framwork.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by creasypita on 8/21/2019.
 */
public class BeanHelper {

    private static Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

    private static Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
    static{
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object beanInstance = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, beanInstance);
        }

    }

    public static Map<Class<?>, Object> getBeanMap()
    {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls)
    {
        if(!BEAN_MAP.containsKey(cls))
        {
            throw new RuntimeException("can not get bean by class " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    public static void setBean(Class<?> beanClass,Object beanInstance )
    {
        BEAN_MAP.put(beanClass, beanInstance);
    }
}
