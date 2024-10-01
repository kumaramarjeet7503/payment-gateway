package com.example.razorpay_pg.controller;

import com.example.razorpay_pg.resource.BaseInteraction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RazorPayController implements BaseInteraction {

    @Override
    public String welcome() {
        return "Welcome to the razor pay payment gateway";
    }

    @Override
    public String test() {
        return "This is the test method" ;
    }


}
