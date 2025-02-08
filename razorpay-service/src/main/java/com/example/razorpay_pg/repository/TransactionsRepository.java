package com.example.razorpay_pg.repository;

import com.example.razorpay_pg.entity.APILog;
import com.example.razorpay_pg.entity.Transactions;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends BaseRepository<Transactions,Long> {
    Transactions findOneByTrxnId(String trxnId);
}
