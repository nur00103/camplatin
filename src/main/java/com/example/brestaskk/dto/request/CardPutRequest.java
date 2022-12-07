package com.example.brestaskk.dto.request;

import com.example.brestaskk.enums.CardStatusEnum;

public class CardPutRequest {
    private CardStatusEnum status;

    public CardPutRequest() {
    }

    public CardPutRequest(CardStatusEnum status) {
        this.status = status;
    }

    public CardStatusEnum getStatus() {
        return status;
    }

    public CardPutRequest setStatus(CardStatusEnum status) {
        this.status = status;
        return this;
    }
}
