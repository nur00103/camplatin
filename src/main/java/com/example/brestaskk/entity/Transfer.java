package com.example.brestaskk.entity;

import com.example.brestaskk.enums.CurrencyEnum;
import com.example.brestaskk.enums.TransferStatusEnum;
import com.example.brestaskk.enums.TransferTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "transfer")
public class Transfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "debitor_card_id")
    private Long debitorCardID;

    @Column(name = "creditor_card_id")
    private Long creditorCardID;

    @Column(name = "debitor_account_id")
    private Long debitorAccountID;

    @Column(name = "creditor_account_id")
    private Long creditorAccountID;

    @Column(name = "creditor_amount")
    private BigDecimal creditorAmount;
    @Column(name = "debitor_amount")
    private BigDecimal debitorAmount;

    public Transfer(Long id, Long debitorCardID, Long creditorCardID, Long debitorAccountID, Long creditorAccountID, BigDecimal creditorAmount, BigDecimal debitorAmount, CurrencyEnum debitorCurrency, CurrencyEnum creditorCurrency, TransferTypeEnum transferType, TransferStatusEnum status, Integer active, Date createDate) {
        this.id = id;
        this.debitorCardID = debitorCardID;
        this.creditorCardID = creditorCardID;
        this.debitorAccountID = debitorAccountID;
        this.creditorAccountID = creditorAccountID;
        this.creditorAmount = creditorAmount;
        this.debitorAmount = debitorAmount;
        this.debitorCurrency = debitorCurrency;
        this.creditorCurrency = creditorCurrency;
        this.transferType = transferType;
        this.status = status;
        this.active = active;
        this.createDate = createDate;
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public Transfer setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
        return this;
    }

    @Column(name = "debitor_currency",length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum debitorCurrency;

    @Column(name = "creditor_currency",length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum creditorCurrency;

    @Column(name = "transfer_type")
    @Enumerated(EnumType.ORDINAL)
    private TransferTypeEnum transferType;


    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private TransferStatusEnum status;


    @Column(name = "active")
    private Integer active=1;

    @Column(name = "create_date")
    private Date createDate= java.sql.Date.valueOf(LocalDate.now());

    public Transfer() {
    }

    public Transfer(Long id, Long debitorCardID, Long creditorCardID, Long debitorAccountID, Long creditorAccountID, BigDecimal amount, CurrencyEnum debitorCurrency, CurrencyEnum creditorCurrency, TransferTypeEnum transferType, TransferStatusEnum status, Integer active, Date createDate) {
        this.id = id;
        this.debitorCardID = debitorCardID;
        this.creditorCardID = creditorCardID;
        this.debitorAccountID = debitorAccountID;
        this.creditorAccountID = creditorAccountID;
        this.debitorCurrency = debitorCurrency;
        this.creditorAmount = amount;
        this.creditorCurrency = creditorCurrency;
        this.transferType = transferType;
        this.status = status;
        this.active = active;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public Transfer setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDebitorCardID() {
        return debitorCardID;
    }

    public Transfer setDebitorCardID(Long debitorCardID) {
        this.debitorCardID = debitorCardID;
        return this;
    }

    public Long getCreditorCardID() {
        return creditorCardID;
    }

    public Transfer setCreditorCardID(Long creditorCardID) {
        this.creditorCardID = creditorCardID;
        return this;
    }

    public Long getDebitorAccountID() {
        return debitorAccountID;
    }

    public Transfer setDebitorAccountID(Long debitorAccountID) {
        this.debitorAccountID = debitorAccountID;
        return this;
    }

    public Long getCreditorAccountID() {
        return creditorAccountID;
    }

    public Transfer setCreditorAccountID(Long creditorAccountID) {
        this.creditorAccountID = creditorAccountID;
        return this;
    }

    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    public Transfer setCreditorAmount(BigDecimal creditorAmount) {
        this.creditorAmount = creditorAmount;
        return this;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public Transfer setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
        return this;
    }

    public CurrencyEnum getCreditorCurrency() {
        return creditorCurrency;
    }

    public Transfer setCreditorCurrency(CurrencyEnum creditorCurrency) {
        this.creditorCurrency = creditorCurrency;
        return this;
    }

    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public Transfer setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
        return this;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public Transfer setStatus(TransferStatusEnum status) {
        this.status = status;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public Transfer setActive(Integer active) {
        this.active = active;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Transfer setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", debitorCardID=" + debitorCardID +
                ", creditorCardID=" + creditorCardID +
                ", debitorAccountID=" + debitorAccountID +
                ", creditorAccountID=" + creditorAccountID +
                ", debitorCurrency=" + debitorCurrency +
                ", amoun=" + creditorAmount +
                ", creditorCurrency=" + creditorCurrency +
                ", transferType=" + transferType +
                ", status=" + status +
                ", active=" + active +
                ", createDate=" + createDate +
                '}';
    }
}
