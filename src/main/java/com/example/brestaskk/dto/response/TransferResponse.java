package com.example.brestaskk.dto.response;

import com.example.brestaskk.enums.CurrencyEnum;
import com.example.brestaskk.enums.TransferStatusEnum;
import com.example.brestaskk.enums.TransferTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransferResponse implements Serializable {
    private Long id;

    private Long debitorCardID;

    private Long creditorCardID;

    private Long debitorAccountID;

    private Long creditorAccountID;

    private CurrencyEnum debitorCurrency;

    private BigDecimal creditorAmount;
    private BigDecimal debitorAmount;

    private CurrencyEnum creditorCurrency;

    private TransferTypeEnum transferType;

    private TransferStatusEnum status;

    private Integer active;

    private Date createDate;

    public TransferResponse(Long id, Long debitorCardID, Long creditorCardID, Long debitorAccountID, Long creditorAccountID, CurrencyEnum debitorCurrency, BigDecimal creditorAmount, BigDecimal debitorAmount, CurrencyEnum creditorCurrency, TransferTypeEnum transferType, TransferStatusEnum status, Integer active, Date createDate) {
        this.id = id;
        this.debitorCardID = debitorCardID;
        this.creditorCardID = creditorCardID;
        this.debitorAccountID = debitorAccountID;
        this.creditorAccountID = creditorAccountID;
        this.debitorCurrency = debitorCurrency;
        this.creditorAmount = creditorAmount;
        this.debitorAmount = debitorAmount;
        this.creditorCurrency = creditorCurrency;
        this.transferType = transferType;
        this.status = status;
        this.active = active;
        this.createDate = createDate;
    }

    public TransferResponse(Long id, Long debitorCardID, Long creditorCardID, Long debitorAccountID, Long creditorAccountID, CurrencyEnum debitorCurrency, BigDecimal creditorAmount, CurrencyEnum creditorCurrency, TransferTypeEnum transferType, TransferStatusEnum status, Integer active, Date createDate) {
        this.id = id;
        this.debitorCardID = debitorCardID;
        this.creditorCardID = creditorCardID;
        this.debitorAccountID = debitorAccountID;
        this.creditorAccountID = creditorAccountID;
        this.debitorCurrency = debitorCurrency;
        this.creditorAmount = creditorAmount;
        this.creditorCurrency = creditorCurrency;
        this.transferType = transferType;
        this.status = status;
        this.active = active;
        this.createDate = createDate;
    }

    public TransferResponse() {
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public TransferResponse setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDebitorCardID() {
        return debitorCardID;
    }

    public void setDebitorCardID(Long debitorCardID) {
        this.debitorCardID = debitorCardID;
    }

    public Long getCreditorCardID() {
        return creditorCardID;
    }

    public void setCreditorCardID(Long creditorCardID) {
        this.creditorCardID = creditorCardID;
    }

    public Long getDebitorAccountID() {
        return debitorAccountID;
    }

    public void setDebitorAccountID(Long debitorAccountID) {
        this.debitorAccountID = debitorAccountID;
    }

    public Long getCreditorAccountID() {
        return creditorAccountID;
    }

    public void setCreditorAccountID(Long creditorAccountID) {
        this.creditorAccountID = creditorAccountID;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public void setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
    }

    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    public void setCreditorAmount(BigDecimal creditorAmount) {
        this.creditorAmount = creditorAmount;
    }

    public CurrencyEnum getCreditorCurrency() {
        return creditorCurrency;
    }

    public void setCreditorCurrency(CurrencyEnum creditorCurrency) {
        this.creditorCurrency = creditorCurrency;
    }

    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransferStatusEnum status) {
        this.status = status;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "TransferResponse{" +
                "id=" + id +
                ", debitorCardID=" + debitorCardID +
                ", creditorCardID=" + creditorCardID +
                ", debitorAccountID=" + debitorAccountID +
                ", creditorAccountID=" + creditorAccountID +
                ", debitorCurrency=" + debitorCurrency +
                ", creditorAmount=" + creditorAmount +
                ", creditorCurrency=" + creditorCurrency +
                ", transferType=" + transferType +
                ", status=" + status +
                ", active=" + active +
                ", createDate=" + createDate +
                '}';
    }
}
