package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framwork.util.ClassUtil;

/**
 * Created by creasypita on 8/21/2019.
 */
public class HelperLoader {
    public static void init()
    {
        Class<?>[] clses = new Class[]{
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : clses) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
