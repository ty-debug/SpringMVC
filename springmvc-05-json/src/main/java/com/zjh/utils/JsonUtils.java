package com.zjh.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JsonUtils {

    //    不重复造轮子
    public static String getJson(Object obj) {
        return getJson(obj, "yyyy-HH-dd HH:mm:ss");
    }

    public static String getJson(Object obj, String dateFormat) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            mapper.setDateFormat(sdf);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
