package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;
import org.smart4j.framework.proxy.TransactionProxy;

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
            //切面类与targetclass的map,记录每个切面类与目标类的切入管理,比如这里的 controllerAspect -》CustomerController
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            //proxyMap 反转得到 目标类targetclass与切面类的map关系，比如某个controller有 日志和事物的切面
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> entry : targetMap.entrySet()) {
                Class<?> targetClass = entry.getKey();
                List<Proxy> proxyList = entry.getValue();
                //创建proxy,包装最终执行目标类和所有切面类的 一个代理链
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                //把这个proxy和目标类做绑定放到bean容器，目标类的执行即会执行这个proxy
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
        proxyMap.putAll(createTransactionProxyMap());
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

    private static Map<Class<?>, Set<Class<?>>> createTransactionProxyMap(){
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> set = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class, set);
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
