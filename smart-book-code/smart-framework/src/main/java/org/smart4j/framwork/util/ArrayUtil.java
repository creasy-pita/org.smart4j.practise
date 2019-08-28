package org.smart4j.framwork.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by creasypita on 8/28/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class ArrayUtil  {

    /**
     * 判断数组是否非空
     * @param array
     * @return
     */
    public static boolean isNotEmpty(Object[] array)
    {
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array)
    {
        return ArrayUtils.isEmpty(array);
    }


}
