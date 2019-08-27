package org.smart4j.framework.helper;

import org.smart4j.framework.bean.FormParam;
import org.smart4j.framwork.util.CodecUtil;
import org.smart4j.framwork.util.StreamUtil;
import org.smart4j.framwork.util.StringUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by creasypita on 8/27/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class RequestHelper {
    public static List<FormParam> createParam(HttpServletRequest request)
    {
        List<FormParam> formParams = new ArrayList<FormParam>();
        formParams.addAll(parseParamNames(request));
        formParams.addAll(parseInputStream(request));
        return formParams;
    }

    public static List<FormParam> parseParamNames(HttpServletRequest request)
    {
        List<FormParam> formParamList = new ArrayList<FormParam>();
        Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements())
        {
            String fieldName = paramNames.nextElement();
            String[] fieldValues = request.getParameterValues(fieldName);
            if(fieldValues.length>0)
            {
                String fieldValue;
                if (fieldValues.length == 1) {
                    fieldValue = fieldValues[0];
                } else {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < fieldValues.length; i++) {
                        sb.append(fieldValues[i]);
                        if (i != fieldValues.length - 1) {
                            sb.append(StringUtil.SEPARATOR);
                        }
                    }
                    fieldValue = sb.toString();
                }
                formParamList.add(new FormParam(fieldName, fieldValue));
            }
        }
        return formParamList;
    }

    public static List<FormParam> parseInputStream(HttpServletRequest request){
        List<FormParam> formParamList = new ArrayList<FormParam>();
        try {
            String bodyStr = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            String[]  kvs = StringUtil.splitString(bodyStr, "&"); // bodyStr.split(StringUtil.SEPARATOR);
            for (String kv :
                    kvs) {
                String[] array = StringUtil.splitString(kv,"=");
                if(array.length ==2) {
                    String fieldName = array[0];
                    String fieldValue = array[1];
                    formParamList.add(new FormParam(fieldName, fieldValue));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return formParamList;
    }




}
