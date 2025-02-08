package com.example.razorpay_pg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class Payments {

    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("reminder_enable")
    private boolean reminderEnabled;
    private List<String> reminders ;
}
