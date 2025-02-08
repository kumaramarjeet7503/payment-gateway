package com.example.razorpay_pg.service;

import com.example.razorpay_pg.entity.Transactions;
import com.example.razorpay_pg.model.WebhookResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final static Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final TransactionService transactionService ;
    public PaymentService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void captureWebhook(String rawUrl) {
        Map<String, String> queryParams = this.parseQueryParams(rawUrl);
        WebhookResponse webhookResponse = this.convertToWebhookResponse(queryParams);
        if(webhookResponse != null) {
            Transactions trxn =  transactionService.getTransactionById(webhookResponse.getRazorpayPaymentLinkReferenceId());
            if(trxn != null){
                trxn.setWebhookResponse(rawUrl);
                trxn.setRzpSignature(webhookResponse.getRazorpaySignature());
                trxn.setStatus(webhookResponse.getRazorpayPaymentLinkStatus().equals("paid") ? 1 : trxn.getStatus() );
                trxn.setUtrNumber(webhookResponse.getRazorpayPaymentId());
                transactionService.saveTransaction(trxn);
                logger.info(trxn.getTrxnId()+" is successfully captured and updated with status "+webhookResponse.getRazorpayPaymentLinkStatus());
            }
        }
    }

    public static Map<String, String> parseQueryParams(String rawUrl) {
        Map<String, String> queryParams = new HashMap<>();

        try {
            // Extract query parameters part of the URL (everything after the '?' symbol)
            String queryString = rawUrl.contains("?") ? rawUrl.split("\\?")[1] : rawUrl;
            // Split the query string by '&', then split each part by '=' and map them into a Map
            queryParams = java.util.Arrays.stream(queryString.split("&"))
                    .map(param -> param.split("="))
                    .filter(p -> p.length == 2) // Ensure each param contains both key and value
                    .collect(Collectors.toMap(p -> p[0], p -> p[1]));
        } catch (Exception e) {
            // Handle any exceptions that may occur, such as NullPointerException, ArrayIndexOutOfBoundsException, etc.
            logger.error("Error parsing query parameters: " + e.getMessage());
        }
        return queryParams;
    }

    public static WebhookResponse convertToWebhookResponse(Map<String, String> queryParams) {
        WebhookResponse webhookResponse = null;
        try {
            // Convert the queryParams map to WebhookResponse object
            ObjectMapper objectMapper = new ObjectMapper();
            webhookResponse = objectMapper.convertValue(queryParams, WebhookResponse.class);
        } catch (Exception e) {
            // General exception handling for any other unexpected errors
            logger.error("Unexpected error during conversion: " + e.getMessage());
        }
        return webhookResponse;
    }

}
