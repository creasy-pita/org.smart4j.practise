package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framwork.util.ReflectionUtil;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by creasypita on 8/21/2019.
 */
public class IocHelper {
    static{
        Map<Class<?>, Object> beanmap = BeanHelper.getBeanMap();
        for (Map.Entry<Class<?>, Object> entry: beanmap.entrySet()) {
            Class<?> beanClass = entry.getKey();
            Object beanInstance = entry.getValue();
            Field[] beanFields = beanClass.getDeclaredFields();
            for (Field beanField : beanFields) {
                if (beanField.isAnnotationPresent(Inject.class))
                {
                    Object injectInstance = beanmap.get(beanField.getType());
                    ReflectionUtil.setField(beanInstance, beanField, injectInstance);
                }
            }
        }
    }
}
