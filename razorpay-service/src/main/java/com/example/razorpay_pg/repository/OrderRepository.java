package com.example.razorpay_pg.repository;

import com.example.razorpay_pg.entity.Orders;
import com.razorpay.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

}
