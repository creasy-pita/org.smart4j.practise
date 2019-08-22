package org.smart4j.framework.bean;

/**
 * Created by creasypita on 8/22/2019.
 */
public class FormParam {
    private String fieldName;
    private String fieldValue;

    public FormParam(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
