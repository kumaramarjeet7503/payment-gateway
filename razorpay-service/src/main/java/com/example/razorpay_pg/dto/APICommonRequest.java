package com.example.razorpay_pg.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class APICommonRequest {

    private Integer amount;
    private String currency ;
    private String referenceId;
    private String description;
    private String name;
    private String contact;
    private String email ;

    public String toJson() {
        String json = "" ;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
             json = objectMapper.writeValueAsString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json ;
    }
}
