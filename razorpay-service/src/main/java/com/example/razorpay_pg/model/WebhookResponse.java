package com.example.razorpay_pg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude
@Getter
@Setter
@ToString
public class WebhookResponse {

    @JsonProperty("razorpay_payment_id")
    private String razorpayPaymentId;
    @JsonProperty("razorpay_payment_link_id")
    private String razorpayPaymentLinkId;
    @JsonProperty("razorpay_payment_link_reference_id")
    private String razorpayPaymentLinkReferenceId;
    @JsonProperty("razorpay_payment_link_status")
    private String razorpayPaymentLinkStatus;
    @JsonProperty("razorpay_signature")
    private String razorpaySignature;

}
