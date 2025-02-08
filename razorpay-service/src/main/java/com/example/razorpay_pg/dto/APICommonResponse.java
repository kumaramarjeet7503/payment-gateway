package com.example.razorpay_pg.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class APICommonResponse {

        private Integer status;
        private String message;
        private String response;
        private String referenceId;
        private String transactionId;

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
