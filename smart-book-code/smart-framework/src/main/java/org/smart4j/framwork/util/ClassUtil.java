package org.smart4j.framwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by creasypita on 8/21/2019.
 */
public class ClassUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader(){return  Thread.currentThread().getContextClassLoader();}

    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url != null)
                {
                    String protocol = url.getProtocol();
                    if(protocol.equals("file"))
                    {
                        String packagePath = url.getPath().replaceAll("%20"," ");
                        addClass(classSet, packagePath, packageName);
                    }
                    else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }

                }
                //if file

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classSet;
    }

    public static void addClass(Set<Class<?>> classSet, String packagePath, String packageName)
    {
        File[] files = new File(packagePath).listFiles();
        for (File file:files) {
            String fileName = file.getName();
            if(file.isFile())
            {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                className = packageName + "." +className;
                doAddClass(classSet, className);
            }
            else
            {
                String subPackagePath = fileName;
                subPackagePath = packagePath + "/" + subPackagePath;
                String subPackageName = fileName;
                subPackageName = packageName + "." + subPackageName;
                addClass(classSet, subPackagePath, subPackageName);
            }
        }

    }

    public static void doAddClass(Set<Class<?>> classSet,String className){
        classSet.add(loadClass(className, false));
    }

    public static Class<?> loadClass(String className, boolean initialize)
    {
        Class<?> cls;
        try {
            cls = Class.forName(className, initialize, getClassLoader());
            return cls;
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure", e);
            throw new RuntimeException(e);
        }
    }

    public static Class<?> loadClass(String className)
    {
        return loadClass(className, true);
    }

}
