package org.smart4j.chapter4.test;

import org.junit.Assert;
import org.junit.Test;
import org.smart4j.framework.ConfigConstant;
import org.smart4j.framwork.util.PropsUtil;

import java.util.Properties;

/**
 * Created by creasypita on 8/24/2019.
 */
public class PropsUtilTest {
    @Test
    public void PropsUtil_getStringByKey_ReturnTrueValue() throws Exception {
        Properties properties = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
        Assert.assertEquals("org.smart4j.chapter4", PropsUtil.getString(properties, ConfigConstant.APP_BASE_PACKAGE));
    }
}
