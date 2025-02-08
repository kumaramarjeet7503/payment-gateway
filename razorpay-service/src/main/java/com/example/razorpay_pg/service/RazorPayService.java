package com.example.razorpay_pg.service;

import com.example.razorpay_pg.communication.RazorpayCommunication;
import com.example.razorpay_pg.config.AppConfig;
import com.example.razorpay_pg.constants.RazorpayConstants;
import com.example.razorpay_pg.dto.APICommonRequest;
import com.example.razorpay_pg.dto.RazorpayRequest;
import com.example.razorpay_pg.dto.RazorpayResponse;
import com.example.razorpay_pg.entity.APILog;
import com.example.razorpay_pg.entity.Transactions;
import com.example.razorpay_pg.helper.RazorpayHelper;
import com.example.razorpay_pg.model.Customer;
import com.example.razorpay_pg.repository.APILogRepository;
import com.example.razorpay_pg.repository.TransactionsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class RazorpayService {

    private final RazorpayCommunication razorPayCommunication ;
    private final AppConfig appConfig ;
    private final APILogRepository apiLogRepo ;
    private final TransactionsRepository transactionsRepo ;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.hostname}")
    private String hostName;

    @Autowired
    public RazorpayService(RazorpayCommunication razorPayCommunication, AppConfig appConfig, APILogRepository apiLogRepo, TransactionsRepository transactionsRepo) {
        this.razorPayCommunication = razorPayCommunication;
        this.appConfig = appConfig;
        this.apiLogRepo = apiLogRepo ;
        this.transactionsRepo = transactionsRepo ;
    }

    public String getAllPaymentsInfo(){

        String response = razorPayCommunication.getRequest(appConfig.getRazorpayUrl(),appConfig.getRazorpayUsername()+":"+appConfig.getRazorpayPassword()) ;
        return response;
    }

    @Transactional
    public String makePayment(APICommonRequest apiCommonRequest) throws JsonProcessingException {

        APILog apiLog = this.logApi(apiCommonRequest) ;
        Transactions trxn = createTransaction(apiCommonRequest) ;
        apiLogRepo.save(apiLog);
        ObjectMapper objectMapper = new ObjectMapper();
        String razorpayRequest = RazorpayHelper.toStringJson(createPaymentRequest(apiCommonRequest));

        String response = razorPayCommunication.postRequest(appConfig.getRazorpayUrl(),appConfig.getRazorpayUsername()+":"+appConfig.getRazorpayPassword(), razorpayRequest) ;
        apiLog.setApiResponse(response);
        apiLogRepo.save(apiLog);
        if(!StringUtils.isBlank(response)){
            RazorpayResponse razorpayResponse = objectMapper.readValue(response, RazorpayResponse.class);
            trxn.setPaymentLinkId(razorpayResponse.getPayment_link_id());
            trxn.setPaymentUrl(razorpayResponse.getShortUrl());
            trxn.setLinkExpireBy(new Date(razorpayResponse.getExpireBy() * 1000));
            trxn.setPaymentLinkId(razorpayResponse.getPayment_link_id());
            trxn.setPaymentUrl(razorpayResponse.getShortUrl());
            transactionsRepo.save(trxn);
        }
        return response;
    }

    private RazorpayRequest createPaymentRequest(APICommonRequest request){

        RazorpayRequest razorpayRequest = new RazorpayRequest();
        Customer customer = new Customer(request.getName(),request.getContact(),request.getEmail());
        razorpayRequest.setAmount(Math.round(request.getAmount()*100));
        razorpayRequest.setCurrency(RazorpayConstants.PAYMENT_CURRENCY);
        razorpayRequest.setExpireBy(Instant.now().getEpochSecond() + Duration.ofMinutes(20).getSeconds());
        razorpayRequest.setReferenceId(request.getReferenceId());
        razorpayRequest.setDescription(request.getDescription());
        razorpayRequest.setCustomer(customer);
        razorpayRequest.setCallbackUrl("http://"+hostName+":"+serverPort+contextPath+appConfig.getCallbackUrl());
        razorpayRequest.setCallbackMethod(RazorpayConstants.CALLBACK_METHOD_GET);

        return razorpayRequest;
    }

    private APILog logApi(APICommonRequest apiCommonRequest){
        APILog apiLog = new APILog();
        apiLog.setApiRequest(apiCommonRequest.toJson());
        apiLog.setReferenceId(apiCommonRequest.getReferenceId());
        apiLog.setCreatedAt(new Date());
        apiLog.setUpdatedAt(new Date());
        return apiLog ;
    }

    private Transactions createTransaction(APICommonRequest request){
        Transactions transactions = new Transactions();

        transactions.setAmount(request.getAmount());
        transactions.setCreatedAt(new Date());
        transactions.setUpdatedAt(new Date());
        transactions.setStatus(0);
        transactions.setName(request.getName());
        transactions.setTrxnId(request.getReferenceId());
        transactions.setDescription(request.getDescription());
        transactions.setContact(request.getContact());
        transactionsRepo.save(transactions) ;
        return transactions;
    }
}
