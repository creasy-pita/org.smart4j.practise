package org.smart4j.framwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by creasypita on 8/24/2019.
 */
public class PropsUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName)
    {
        Properties properties = new Properties();
        InputStream is = ClassUtil.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(is);
        } catch (IOException e) {
            LOGGER.error("load properties failure", e);
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                LOGGER.error("close properties inputstream failure", e);
            }
        }
        return properties;
    }

    public static String getString(Properties properties, String key)
    {
        return getString(properties, key, "");
    }
    /**
     * 获取指定key的属性值 可指定默认值
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key,String defaultValue)
    {
        String value = defaultValue;
        if(properties.containsKey(key))
        {
            value = properties.getProperty(key);
        }
        return value;
    }
}
