package com.example.brestaskk.dto.request;

import com.example.brestaskk.enums.AccountStatusEnum;

public class AccountPutRequest {

    private AccountStatusEnum status;

    public AccountPutRequest(AccountStatusEnum status) {
        this.status = status;
    }

    public AccountPutRequest() {
    }

    public AccountStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AccountStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountPutRequest{" +
                "status=" + status +
                '}';
    }
}
