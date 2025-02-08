package com.example.razorpay_pg.dto;

import com.example.razorpay_pg.model.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class RazorpayRequest {

    private long amount;
    private String currency;
    @JsonProperty("expire_by")
    private long expireBy;
    @JsonProperty("reference_id")
    private String referenceId;
    private String description;
    private Customer customer;
    @JsonProperty("callback_url")
    private String callbackUrl;
    @JsonProperty("callback_method")
    private String callbackMethod;
}
