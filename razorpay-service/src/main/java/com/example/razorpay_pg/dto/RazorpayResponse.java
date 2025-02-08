package com.example.razorpay_pg.dto;

import com.example.razorpay_pg.model.Customer;
import com.example.razorpay_pg.model.Notify;
import com.example.razorpay_pg.model.Payments;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RazorpayResponse {

    @JsonProperty("accept_partial")
    private boolean acceptPartial;
    private int amount;
    @JsonProperty("accept_paid")
    private int amountPaid;
    @JsonProperty("callback_method")
    private String callbackMethod;
    @JsonProperty("callback_url")
    private String callbackUrl;
    @JsonProperty("cancelled_at")
    private int cancelledAt;
    @JsonProperty("created_at")
    private long createdAt;
    private String currency;
    private Customer customer;
    private String description;
    @JsonProperty("expire_by")
    private long expireBy;
    @JsonProperty("expire_at")
    private long expiredAt;
    @JsonProperty("first_min_partial_amount")
    private int firstMinPartialAmount;
    @JsonProperty("id")
    private String payment_link_id;
    private Object notes; // It is null in the example, but can be changed based on real data
    private Notify notify;
    private Payments payments; // Can be null or a list of payment objects depending on the actual response
    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("reminder_enable")
    private boolean reminderEnable;
    @JsonProperty("short_url")
    private String shortUrl;
    private String status;
    @JsonProperty("updated_at")
    private long updatedAt;
    @JsonProperty("upi_link")
    private boolean upiLink;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("whatsapp_link")
    private boolean whatsappLink;
}
