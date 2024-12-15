package com.example.razorpay_pg.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppConfig {
    private String message ;
    private String razorpayUrl ;
    private String razorpayUsername ;
    private String razorpayPassword ;
    private String callbackUrl ;
}
