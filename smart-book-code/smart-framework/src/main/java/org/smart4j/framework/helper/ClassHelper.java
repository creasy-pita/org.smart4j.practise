package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framwork.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by creasypita on 8/21/2019.
 */
public class ClassHelper {

    private static Set<Class<?>>  CLASS_SET;
    static {
        String basePackage = "org.smart4j.chapter3";
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }
    
    public static Set<Class<?>> getBeanClassSet()
    {
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return  beanClassSet;
    }
    
    private static Set<Class<?>> getServiceClassSet()
    {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Service.class))
            {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet()
    {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Controller.class))
            {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下指定父类（或接口）的所有子类（或接口）
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass)
    {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if(superClass.isAssignableFrom(cls) && !superClass.equals(cls))
            {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下指定注解的所有类
     * @param annotationClass
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass)
    {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(annotationClass))
            {
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
