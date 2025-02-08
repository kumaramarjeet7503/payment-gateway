package com.example.razorpay_pg.controller;

import com.example.razorpay_pg.constants.RazorpayConstants;
import com.example.razorpay_pg.entity.Transactions;
import com.example.razorpay_pg.model.WebhookResponse;
import com.example.razorpay_pg.service.PaymentService;
import com.example.razorpay_pg.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.Map;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/callback")
    private void receivePaymentCallback(RequestEntity<String> request) {
        String rawUrl = request.getUrl().toString();
        paymentService.captureWebhook(rawUrl);
    }

}
