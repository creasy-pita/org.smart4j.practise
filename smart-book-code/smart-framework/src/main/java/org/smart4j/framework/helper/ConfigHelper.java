package org.smart4j.framework.helper;

/**
 * Created by creasypita on 8/22/2019.
 */
public final class  ConfigHelper {
    /**
     * 获取应用 JSP 路径
     */
    public static String getAppJspPath() {
        return "/WEB-INF/view/";//TBD
        //return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }
}
