package com.example.razorpay_pg.service;

import com.example.razorpay_pg.config.AppPropertyConfig;
import com.example.razorpay_pg.constants.OrderConstants;
import com.example.razorpay_pg.entity.Orders;
import com.example.razorpay_pg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo ;

    @Autowired
    private AppPropertyConfig appConfig ;

    public Orders getOrderById(String orderId)
    {
        Optional<Orders> order = orderRepo.findById(Integer.parseInt(orderId)) ;
        return order.orElseGet(null) ;
    }

    public List<Orders> getAllOrder()
    {
        List<Orders> orders = orderRepo.findAll() ;
        return orders ;
    }

    public void createOrder(Orders order)
    {
        order.setCreatedAt(new Date());
        order.setStatus(OrderConstants.ORDER_INITIATED);
        Orders orderCaptured = orderRepo.save(order) ;

//        return orders ;
    }


}
