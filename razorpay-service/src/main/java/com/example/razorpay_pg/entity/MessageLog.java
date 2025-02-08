package com.example.razorpay_pg.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "message_log")
public class MessageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reference_id")
    private String referenceId ;
    @Column(name = "razorpay_request",length = 1000)
    private String razorpayRequest ;
    @Column(name = "razorpay_response",length = 2000)
    private String razorpayResponse ;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt ;
}
