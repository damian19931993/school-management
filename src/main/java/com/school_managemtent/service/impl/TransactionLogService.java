package com.school_managemtent.service.impl;

import com.school_managemtent.entity.log.TransactionLog;
import com.school_managemtent.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionLogService {

    private final TransactionLogRepository transactionLogRepository;

    @Autowired
    public TransactionLogService(TransactionLogRepository transactionLogRepository) {
        this.transactionLogRepository = transactionLogRepository;
    }

    public void createLog(String operation, String details, String username) {
        TransactionLog transaction = new TransactionLog();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setOperation(operation);
        transaction.setPerformedBy(username);
        transaction.setDetails(details);
        transactionLogRepository.save(transaction);
    }
}
