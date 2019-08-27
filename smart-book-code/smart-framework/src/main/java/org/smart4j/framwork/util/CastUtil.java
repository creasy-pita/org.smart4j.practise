package org.smart4j.framwork.util;

/**
 * Created by creasypita on 8/27/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class CastUtil {
    /**
     * 转为long类型
     * @param obj
     * @return
     */
    public static long castLong(Object obj)
    {
        return castLong(obj, 0);
    }

    /**
     * 转为long类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj,long defaultValue)
    {
        long longValue = defaultValue;
        if(obj!=null)
        {
            String valueStr = castString(obj);
            try {
                longValue = Long.parseLong(valueStr);
            }
            catch (NumberFormatException e){}
        }
        return longValue;
    }

    public static String castString(Object obj)
    {
        return castString(obj, "");
    }

    public static String castString(Object obj, String defaultValue)
    {
        return obj==null? defaultValue: String.valueOf(obj);
    }
}
