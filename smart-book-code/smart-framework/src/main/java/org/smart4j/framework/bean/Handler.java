package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by creasypita on 8/21/2019.
 */
public class Handler {
    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    /**
     * Action 方法
     */
    private Method actionMethod;
}
