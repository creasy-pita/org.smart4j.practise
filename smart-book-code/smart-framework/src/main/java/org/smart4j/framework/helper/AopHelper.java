package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

import static org.smart4j.framework.helper.ClassHelper.getClassSetByAnnotation;

/**
 * Created by creasypita on 8/23/2019.
 */
public class AopHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static{
        try{
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> entry : targetMap.entrySet()) {
                Class<?> targetClass = entry.getKey();
                List<Proxy> proxyList = entry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxy);
            }
        }
        catch (Exception e)
        {
            LOGGER.error(" aop init failure",e);
            throw new RuntimeException(e);
        }

    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap()
    {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        proxyMap.putAll(createControllerProxyMap());
        return proxyMap;
    }

    private static Map<Class<?>, Set<Class<?>>> createControllerProxyMap()
    {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        //继承 AspectProxy 且 class annotion 为 Aspect的
        Set<Class<?>> proxySet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass : proxySet) {
            if(proxyClass.isAnnotationPresent(Aspect.class))
            {
                //获取aspect 代理类 的代理的目标类集合（此处目标类是 注解为 Aspect.value()的类)
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Class<? extends Annotation> annotation = aspect.value();
                //TBD 可能存在bug
                if(annotation!=null)
                {
                    Set<Class<?>> targetClassSet = ClassHelper.getClassSetByAnnotation(annotation);
                    proxyMap.put(proxyClass, targetClassSet);
                }
            }
        }
        return proxyMap;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry: proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetSet = proxyEntry.getValue();
            for (Class<?> targetClass : targetSet) {
                List<Proxy> proxyList = null;
                if(targetMap.containsKey(targetClass))
                {
                    proxyList = targetMap.get(targetClass);
                }
                if(proxyList==null){
                    proxyList = new ArrayList<Proxy>();
                }
                Proxy proxy =(Proxy) proxyClass.newInstance();
                proxyList.add(proxy);
                targetMap.put(targetClass, proxyList);
            }
        }

        return targetMap;
    }
}
