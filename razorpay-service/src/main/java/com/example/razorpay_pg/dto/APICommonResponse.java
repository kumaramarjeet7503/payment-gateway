package com.example.razorpay_pg.dto;

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
        private String utrNumber;
}
