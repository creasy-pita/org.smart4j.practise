package org.smart4j.framework.bean;

import java.util.List;

/**
 * Created by creasypita on 8/22/2019.
 */
public class Param {
    private List<FormParam> formParamList;

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    public List<FormParam> getFormParamList() {
        return formParamList;
    }
}
