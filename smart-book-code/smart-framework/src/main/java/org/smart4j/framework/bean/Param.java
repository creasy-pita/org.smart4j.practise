package org.smart4j.framework.bean;

import org.smart4j.framwork.util.CastUtil;
import org.smart4j.framwork.util.CollectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数对象
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
        if(CollectionUtil.isNotEmpty(formParamList)) {
            for (FormParam fp : formParamList) {
                String fieldName = fp.getFieldName();
                String fieldValue = fp.getFieldValue();
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }
    /**
     * 获取指定名称的 long类型
     * @param name
     * @return
     */
    public long getLong(String name)
    {
        return CastUtil.castLong(getFieldMap().get(name));
    }

    /**
     * 获取指定名称的 int类型
     * @param name
     * @return
     */
    public int getInt(String name){
        return CastUtil.castInt(getFieldMap().get(name));
    }
}
