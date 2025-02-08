package com.example.razorpay_pg.helper;

import com.example.razorpay_pg.model.WebhookResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RazorpayHelper {

    private static final Logger logger = LoggerFactory.getLogger(RazorpayHelper.class);

    public static String toStringJson(Object obj) {
        String json = "" ;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(obj);
        }catch(Exception e){
            logger.error("An error occurred while processing the request", e);
        }
        return json ;
    }

    // Generic function to convert JSON String to Object
    public static <T> T convertJsonToObject(String json, Class<T> tClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Convert the JSON string to the desired object
            return objectMapper.readValue(json, tClass);
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
        return null; // Return null if there was an error
    }
}
