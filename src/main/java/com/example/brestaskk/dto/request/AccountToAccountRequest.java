package com.example.brestaskk.dto.request;

import com.example.brestaskk.enums.CurrencyEnum;

import java.math.BigDecimal;

public class AccountToAccountRequest {

    private Long creditorAccountID;
    private Long debitorAccountID;
    private BigDecimal creditorAmount;
    private CurrencyEnum debitorCurrency;

    public AccountToAccountRequest() {
    }

    public AccountToAccountRequest(Long creditorAccountID, Long debitorAccountID, BigDecimal creditorAmount, CurrencyEnum debitorCurrency) {
        this.creditorAccountID = creditorAccountID;
        this.debitorAccountID = debitorAccountID;
        this.creditorAmount = creditorAmount;
        this.debitorCurrency = debitorCurrency;
    }

    public Long getCreditorAccountID() {
        return creditorAccountID;
    }

    public AccountToAccountRequest setCreditorAccountID(Long creditorAccountID) {
        this.creditorAccountID = creditorAccountID;
        return this;
    }

    public Long getDebitorAccountID() {
        return debitorAccountID;
    }

    public AccountToAccountRequest setDebitorAccountID(Long debitorAccountID) {
        this.debitorAccountID = debitorAccountID;
        return this;
    }

    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    public AccountToAccountRequest setCreditorAmount(BigDecimal creditorAmount) {
        this.creditorAmount = creditorAmount;
        return this;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public AccountToAccountRequest setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
        return this;
    }
}
