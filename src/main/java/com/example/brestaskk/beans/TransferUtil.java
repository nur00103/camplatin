package com.example.brestaskk.beans;

import com.example.brestaskk.entity.Account;
import com.example.brestaskk.entity.Card;

import java.math.BigDecimal;

public class TransferUtil {

    private Card creditorCard;
    private Card debitorCard;
    private Account creditorAccount;
    private Account debitorAccount;
    private BigDecimal creditorAmount;
    private BigDecimal debitorAmount;


    public TransferUtil() {
    }

    public TransferUtil(Card creditorCard, Card debitorCard, Account creditorAccount, Account debitorAccount, BigDecimal creditorAmount, BigDecimal debitorAmount) {
        this.creditorCard = creditorCard;
        this.debitorCard = debitorCard;
        this.creditorAccount = creditorAccount;
        this.debitorAccount = debitorAccount;
        this.creditorAmount = creditorAmount;
        this.debitorAmount = debitorAmount;
    }

    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    public TransferUtil setCreditorAmount(BigDecimal creditorAmount) {
        this.creditorAmount = creditorAmount;
        return this;
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public TransferUtil setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
        return this;
    }

    public Card getCreditorCard() {
        return creditorCard;
    }

    public TransferUtil setCreditorCard(Card creditorCard) {
        this.creditorCard = creditorCard;
        return this;
    }

    public Card getDebitorCard() {
        return debitorCard;
    }

    public TransferUtil setDebitorCard(Card debitorCard) {
        this.debitorCard = debitorCard;
        return this;
    }

    public Account getCreditorAccount() {
        return creditorAccount;
    }

    public TransferUtil setCreditorAccount(Account creditorAccount) {
        this.creditorAccount = creditorAccount;
        return this;
    }

    public Account getDebitorAccount() {
        return debitorAccount;
    }

    public TransferUtil setDebitorAccount(Account debitorAccount) {
        this.debitorAccount = debitorAccount;
        return this;
    }
}
