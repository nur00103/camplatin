package com.example.brestaskk.service;

import com.example.brestaskk.dto.request.AccountPutRequest;
import com.example.brestaskk.dto.request.AccountRequest;
import com.example.brestaskk.dto.response.AccountResponse;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.entity.Account;

import java.util.List;

public interface AccountService {

    ResponseModel<List<AccountResponse>> getAllAccount();

    public Account convertToEntity(AccountRequest accountRequest);
    public AccountResponse convertToResponse(Account account);

    ResponseModel<AccountResponse> addAccount(AccountRequest accountRequest);

    ResponseModel<AccountResponse> updateAccount(Long accountId, AccountPutRequest accountRequest);

    ResponseModel<AccountResponse> deleteAccount(Long accountId);

    ResponseModel<AccountResponse> getAccountById(Long accountId);

    public boolean checkAccountById(Long accountId);

    public boolean checkAccountRequest(AccountRequest accountRequest);
    public boolean checkCustomerData(Long customerId);
}
