package com.example.razorpay_pg.service;

import com.example.razorpay_pg.communication.RazorPayCommunication;
import com.example.razorpay_pg.config.AppConfig;
import com.example.razorpay_pg.constants.RazorPayConstants;
import com.example.razorpay_pg.dto.APICommonRequest;
import com.example.razorpay_pg.dto.PaymentLinkRequest;
import com.example.razorpay_pg.entity.APILog;
import com.example.razorpay_pg.model.Customer;
import com.example.razorpay_pg.model.Notes;
import com.example.razorpay_pg.model.Notify;
import com.example.razorpay_pg.repository.APILogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class RazorPayService {

    private final RazorPayCommunication razorPayCommunication ;
    private final AppConfig appConfig ;
    private final APILogRepository apiLogRepo ;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    public RazorPayService(RazorPayCommunication razorPayCommunication, AppConfig appConfig, APILogRepository apiLogRepo) {
        this.razorPayCommunication = razorPayCommunication;
        this.appConfig = appConfig;
        this.apiLogRepo = apiLogRepo ;
    }

    public String getAllPaymentsInfo(){

        String response = razorPayCommunication.getRequest(appConfig.getRazorpayUrl(),appConfig.getRazorpayUsername()+":"+appConfig.getRazorpayPassword()) ;
        return response;
    }

    @Transactional
    public String makePayment(APICommonRequest apiCommonRequest) throws JsonProcessingException {

        APILog apiLog = logApi(apiCommonRequest) ;
        apiLogRepo.save(apiLog);
        ObjectMapper objectMapper = new ObjectMapper();
        String paymentLinkRequest = objectMapper.writeValueAsString(createPaymentRequest(apiCommonRequest));
        String response = razorPayCommunication.postRequest(appConfig.getRazorpayUrl(),appConfig.getRazorpayUsername()+":"+appConfig.getRazorpayPassword(),paymentLinkRequest) ;
        apiLog.setApiResponse(response);
        apiLogRepo.save(apiLog);
        return response;
    }

    private PaymentLinkRequest createPaymentRequest(APICommonRequest request){

        PaymentLinkRequest paymentLinkRequest = new PaymentLinkRequest();
        Customer customer = new Customer(request.getName(),request.getContact(),request.getEmail());
        paymentLinkRequest.setAmount(request.getAmount());
        paymentLinkRequest.setCurrency(RazorPayConstants.PAYMENT_CURRENCY);
        paymentLinkRequest.setExpireBy(Instant.now().getEpochSecond() + Duration.ofMinutes(20).getSeconds());
        paymentLinkRequest.setReferenceId(request.getReferenceId());
        paymentLinkRequest.setDescription(request.getDescription());
        paymentLinkRequest.setCustomer(customer);
        paymentLinkRequest.setCallbackUrl("https:/"+contextPath+"/"+appConfig.getCallbackUrl());
        paymentLinkRequest.setCallbackMethod(RazorPayConstants.CALLBACK_METHOD);

        return paymentLinkRequest;
    }

    private APILog logApi(APICommonRequest apiCommonRequest){
        APILog apiLog = new APILog();
        apiLog.setApiRequest(apiCommonRequest.toJson());
        apiLog.setReferenceId(apiCommonRequest.getReferenceId());
        apiLog.setCreatedAt(new Date());
        apiLog.setUpdatedAt(new Date());
        return apiLog ;
    }
}
