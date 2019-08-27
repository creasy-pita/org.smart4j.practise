package org.smart4j.framwork.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by creasypita on 8/27/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class StringUtil {
    public static final  String SEPARATOR = String.valueOf((char)29);

    /**
     * 分割固定格式的字符串
     */
    public static String[] splitString(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }
}
