package com.example.razorpay_pg.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface BaseInteraction {

    @GetMapping
    public String welcome() ;

    @GetMapping("/test")
    public String test() ;

}
