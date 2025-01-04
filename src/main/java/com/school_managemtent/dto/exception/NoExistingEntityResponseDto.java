package com.school_managemtent.dto.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school_managemtent.entity.log.TransactionLog;

public class NoExistingEntityResponseDto {

    private String code;
    private String description;
    private String message;
    @JsonIgnore
    private String username;
    @JsonIgnore
    TransactionLog transactionLog;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransactionLog getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(TransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
