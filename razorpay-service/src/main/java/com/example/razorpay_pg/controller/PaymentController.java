package com.example.razorpay_pg.controller;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/callback")
    private void receivePaymentCallback(HttpRequest request) {
        String rawUrl = request.getURI().toString();
        System.out.println(rawUrl);
    }

}
