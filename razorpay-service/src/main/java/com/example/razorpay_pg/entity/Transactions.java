package com.example.razorpay_pg.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@ToString
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "trxn_id")
    private String trxnId;
    private Double amount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt ;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt ;
    private Integer status;
    private String name;
    private String description;
    private String contact;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "link_expire_by")
    private Date linkExpireBy ;
    @Column(name = "payment_link_id")
    private String paymentLinkId;
    @Column(name = "payment_url")
    private String paymentUrl;
    private String notes;
    @Column(name = "utr_number")
    private String utrNumber ;
    @Column(name = "webhook_response",length = 4000)
    private String webhookResponse ;
    @Column(name ="rzp_signature")
    private String rzpSignature ;

}

