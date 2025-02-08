package com.example.razorpay_pg.communication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Component
public class RazorpayCommunication {

    public final RestTemplate restTemplate;
    public final Logger logger = LoggerFactory.getLogger(RazorpayCommunication.class);

    @Autowired
    public RazorpayCommunication(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getRequest(String url,String auth){
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + encodedAuth);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            logger.info("response: " + response.getBody());
            return response.getBody();
    }

    public String postRequest(String url, String auth, String requestBody) {

        String apiResponse = "" ;
        try{
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            logger.info("Response: {}", response.getBody());
            apiResponse = response.getBody();
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle HTTP error responses
            logger.error("HTTP error occurred: {}", e.getMessage(), e);
        } catch (ResourceAccessException e) {
            // Handle connection issues, timeouts, etc.
            logger.error("Resource access error occurred: {}", e.getMessage(), e);
        } catch(Exception e){
            logger.error("Exception produced for url :{}", url);
        }finally{
            logger.info("Request got completed");
        }
        return apiResponse;
    }

}
