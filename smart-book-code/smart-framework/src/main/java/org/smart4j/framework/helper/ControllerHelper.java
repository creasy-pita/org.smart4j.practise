package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framwork.util.ArrayUtil;
import org.smart4j.framwork.util.ClassUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.smart4j.framework.helper.ClassHelper.getControllerClassSet;

/**
 * Created by creasypita on 8/21/2019.
 * map the request of path and method to the specific action of controller
 */
public class ControllerHelper {
    private static Map<Request,Handler> ACTION_MAP = new HashMap<Request, Handler>();
    static {
        Set<Class<?>> controllerClassSet= ClassHelper.getControllerClassSet();
        for (Class<?> controllerClass: controllerClassSet){
            Method[] methods = controllerClass.getMethods();
            if(ArrayUtil.isNotEmpty(methods)) {
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Action.class)) {
                        String mapping = method.getAnnotation(Action.class).value();
                        if (mapping.matches("\\w+:/\\w*")) {
                            String[] array = mapping.split(":");
                            if (ArrayUtil.isNotEmpty(array)) {
                                String requestMethod = array[0];
                                String requestPath = array[1];
                                Request request = new Request(requestMethod, requestPath);
                                Handler handler = new Handler(controllerClass, method);
                                ACTION_MAP.put(request, handler);
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath)
    {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
