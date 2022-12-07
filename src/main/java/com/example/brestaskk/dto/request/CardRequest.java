package com.example.brestaskk.dto.request;

import com.example.brestaskk.enums.CurrencyEnum;

import java.math.BigDecimal;

public class CardRequest {

    private Long cardNumber;
    private Long accountId;

    public CardRequest() {
    }

    public CardRequest(Long cardNumber, Long accountId) {
        this.cardNumber = cardNumber;
        this.accountId = accountId;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "CardRequest{" +
                "cardNumber=" + cardNumber +
                ", accountId=" + accountId +
                '}';
    }
}
