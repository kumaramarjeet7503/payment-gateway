package com.example.razorpay_pg.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId; // order_id

    private Double amount; // amount

    @Column(name = "created_at")
    private Date createdAt; // created_at

    private Integer status; // status

    @Column(name = "order_ref_id")
    private String orderRefId; // order_ref_id

    @Column(name = "offer_id")
    private String offerId; // offer_id

    @Column(name = "updated_at")
    private Date updatedAt; // updated_at

    private Integer attempts; // attempts

    private String receipt; // receipt
}
