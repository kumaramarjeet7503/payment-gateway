package com.example.razorpay_pg.controller;

import com.example.razorpay_pg.dto.APICommonRequest;
import com.example.razorpay_pg.service.RazorpayService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RazorpayController {

    private final RazorpayService razorPayService;

    RazorpayController(RazorpayService razorPayService) {
        this.razorPayService = razorPayService;
    }

    @GetMapping(value = {"/"})
    public String welcome() {
        return "Welcome to the razor pay payment gateway";
    }

    @GetMapping("test")
    public String test() {
        return "This is the test method" ;
    }

    @GetMapping("/get-all-payment")
    public String getAllPayments() {
        String response = razorPayService.getAllPaymentsInfo() ;
        return response;
    }

    @PostMapping("/make-payment")
    public String makePayment(@RequestBody APICommonRequest apiCommonRequest) {
        String response = "" ;
        try{
             response = razorPayService.makePayment(apiCommonRequest) ;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
