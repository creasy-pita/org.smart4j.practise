package org.smart4j.chapter4.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * Created by creasypita on 8/23/2019.
 */
public class ControllerAspect extends AspectProxy {

    private final  static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin ;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.info("----begin----");
        LOGGER.info(String.format("class %s", cls.getName()));
        LOGGER.info(String.format("method %s", method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("----------- end -----------");
    }
}
