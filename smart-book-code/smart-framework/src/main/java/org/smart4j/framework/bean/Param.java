package org.smart4j.framework.bean;

import org.smart4j.framwork.util.CastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> getFieldMap()
    {
        Map<String,Object> fieldMap = new HashMap<String, Object>();
        for (FormParam fp :
                formParamList) {
            String fieldName = fp.getFieldName();
            String fieldValue = fp.getFieldValue();
            fieldMap.put(fieldName, fieldValue);
        }
        return fieldMap;
    }

    public long getLong(String name)
    {
        return CastUtil.castLong(getFieldMap().get(name));
    }
}
