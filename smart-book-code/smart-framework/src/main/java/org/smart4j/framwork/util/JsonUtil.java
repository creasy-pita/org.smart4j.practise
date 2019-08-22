package org.smart4j.framwork.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by creasypita on 8/22/2019.
 */
public class JsonUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private final static ObjectMapper  OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO 转化为
     * @param object
     * @return
     */
    public static String toJson(Object object)
    {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type)
    {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            LOGGER.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
    }
}
