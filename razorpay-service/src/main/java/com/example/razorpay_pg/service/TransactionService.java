package com.example.razorpay_pg.service;

import com.example.razorpay_pg.entity.Transactions;
import com.example.razorpay_pg.repository.TransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionsRepository transactionsRepository;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public Transactions getTransactionById(String trxnId){
        Transactions transactions = new Transactions();
        try{
            transactions = transactionsRepository.findOneByTrxnId(trxnId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    public void saveTransaction(Transactions transactions){
        try{
            transactionsRepository.save(transactions);
            logger.info("Your transaction has been completed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
