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
    /**
     * 转为 double 类型,默认为0
     * @param obj
     * @return
     */
    public static double castDouble(Object obj) {
        return castDouble(obj, 0);
    }

    /**
     * 转为double类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj, double defaultValue)
    {
        double doubleValue = defaultValue;
        if(obj!=null) {
            String valueStr = castString(obj);
            if(StringUtil.isNotEmpty(valueStr)) {
                try {
                    doubleValue = Double.parseDouble(valueStr);
                } catch (NumberFormatException e) {

                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为 int 类型,默认为0
     * @param obj
     * @return
     */
    public static int castInt(Object obj)
    {
        return castInt(obj, 0);
    }

    /**
     * 转为 int 类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj, int defaultValue)
    {
        int value = defaultValue;
        if(obj!=null) {
            String valueStr = castString(obj);
            if(StringUtil.isNotEmpty(valueStr)) {
                try {
                    value = Integer.parseInt(valueStr);
                } catch (NumberFormatException e) {

                }
            }
        }
        return value;
    }
    /**
     * 转为 boolean 类型,默认为false
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj)
    {
        return castBoolean(obj, false);
    }

    /**
     * 转为 boolean 类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj, boolean defaultValue)
    {
        boolean value = defaultValue;
        if(obj!=null) {
            String valueStr = castString(obj);
            if(StringUtil.isNotEmpty(valueStr)) {
                try {
                    value = Boolean.parseBoolean(valueStr);
                } catch (NumberFormatException e) {

                }
            }
        }
        return value;
    }


    public static String castString(Object obj)
    {
        return castString(obj, "");
    }

    /**
     * 转为 String 类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue)
    {
        return obj==null? defaultValue: String.valueOf(obj);
    }
}
