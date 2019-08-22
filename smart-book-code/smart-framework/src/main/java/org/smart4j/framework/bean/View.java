package org.smart4j.framework.bean;

import java.util.Map;

/**
 * Created by creasypita on 8/22/2019.
 * 视图对象
 */

public class View {

    private String path;

    public String getPath() {
        return path;
    }

    public View(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }

    public View addModel(String key, Object value)
    {
        model.put(key, value);
        return this;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    private Map<String, Object> model;
}
