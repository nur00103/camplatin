package com.example.brestaskk.dto.request;

import com.example.brestaskk.enums.CurrencyEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountRequest {

    @NotNull(message = "Iban cannot be null")
    @NotEmpty(message = "Iban cannot be empty")
    private String iban;
    @NotNull(message = "Account number cannot be null")
    @NotEmpty(message = "Account number cannot be empty")
    private String accountNumber;
    private BigDecimal balance;
    private CurrencyEnum currency;
    @NotNull(message = "Customer cannot be null")
    private Long customerId;

    public AccountRequest() {
    }

    public AccountRequest(String iban, String accountNumber, BigDecimal balance, CurrencyEnum currency, Long customerId) {
        this.iban = iban;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.customerId = customerId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "AccountRequest{" +
                "iban='" + iban + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", customerId=" + customerId +
                '}';
    }
}
