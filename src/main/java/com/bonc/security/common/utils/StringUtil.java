package com.bonc.security.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Create by Mr.ZXF
 * on 2018-12-19 15:36
 */
public class StringUtil {
    private static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(MapperFeature.USE_ANNOTATIONS);

    /**
     * 对象转字符串
     * @param object
     * @return
     */
    public static String objectToString(Object object) {
        String result = null;
        if (isNull(object)) {
            return result;
        }
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对象非空判断
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        if (object != null) {
            return false;
        }else {
            return true;
        }

    }


}
