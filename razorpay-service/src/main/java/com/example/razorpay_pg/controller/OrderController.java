package com.example.razorpay_pg.controller;

import com.example.razorpay_pg.entity.Orders;
import com.example.razorpay_pg.service.OrderService;
import com.razorpay.Order;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("api/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService  ;

    @GetMapping("/fetch/{orderId}")
    public ResponseEntity<Orders> fetchOrderById(@PathParam("orderId") String orderId ){

        Orders order = null ;
        order = orderService.getOrderById(orderId) ;
        return ResponseEntity.ok(order) ;
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<List<Orders>> fetchAllOrder(){
        List<Orders> orders = orderService.getAllOrder() ;
        return ResponseEntity.ok(orders) ;
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order)
    {
        Orders capturedOrder = new Orders() ;
         orderService.createOrder(order);
        return ResponseEntity.ok(capturedOrder)  ;
    }
}
