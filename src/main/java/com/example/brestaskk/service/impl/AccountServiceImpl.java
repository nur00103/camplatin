package com.example.brestaskk.service.impl;

import com.example.brestaskk.dto.request.AccountPutRequest;
import com.example.brestaskk.dto.request.AccountRequest;
import com.example.brestaskk.dto.response.AccountResponse;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.entity.Account;
import com.example.brestaskk.entity.Customer;
import com.example.brestaskk.enums.EnumStatus;
import com.example.brestaskk.enums.ExceptionEnum;
import com.example.brestaskk.exception.MyException;
import com.example.brestaskk.repository.AccountRepository;
import com.example.brestaskk.repository.CustomerRepository;
import com.example.brestaskk.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    Logger logger= LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseModel<List<AccountResponse>> getAllAccount() {
        List<Account> accountList=accountRepository.findAllByActive(EnumStatus.ONLINE.getCode());
        if (accountList.isEmpty()){
            throw new MyException(ExceptionEnum.ACCOUNT_NOT_FOUND);
        }
        List<AccountResponse>accountResponseList=accountList.stream().map(account -> convertToResponse(account))
                .collect(Collectors.toList());
        return ResponseModel.<List<AccountResponse>>builder().result(accountResponseList).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public Account convertToEntity(AccountRequest accountRequest) {
        Account account=new Account();
        account.setIban(accountRequest.getIban());
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setBalance(accountRequest.getBalance());
        account.setCurrency(accountRequest.getCurrency());
        Customer customer=customerRepository.findByIdAndActive(accountRequest.getCustomerId(), EnumStatus.ONLINE.getCode())
                .orElseThrow(()->new MyException(ExceptionEnum.USER_NOT_FOUND));
        account.setCustomer(customer);
        logger.info("AccountNumber:" + account.getAccountNumber());
        return account;
    }

    @Override
    public AccountResponse convertToResponse(Account account) {
        AccountResponse accountResponse=modelMapper.map(account,AccountResponse.class);
        accountResponse.setCustomerId(account.getCustomer().getId());
        return accountResponse;
    }

    @Override
    public ResponseModel<AccountResponse> addAccount(AccountRequest accountRequest) {
        Account account=convertToEntity(accountRequest);
        Account savedAccount=accountRepository.save(account);
        AccountResponse accountResponse=convertToResponse(savedAccount);
        return ResponseModel.<AccountResponse>builder().result(accountResponse).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public ResponseModel<AccountResponse> updateAccount(Long accountId, AccountPutRequest accountRequest) {
//        checkAccountRequest(accountRequest);
        checkAccountById(accountId);
        Account account=accountRepository.findByIdAndActive(accountId,EnumStatus.ONLINE.getCode()).get();
//        account=modelMapper.map(accountRequest,Account.class);
        account.setStatus(accountRequest.getStatus());
        Account updatedAccount=accountRepository.save(account);
        AccountResponse accountResponse=convertToResponse(updatedAccount);
        return ResponseModel.<AccountResponse>builder().result(accountResponse).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public ResponseModel<AccountResponse> deleteAccount(Long accountId) {
        checkAccountById(accountId);
        Account account=accountRepository.findByIdAndActive(accountId,EnumStatus.ONLINE.getCode()).get();
        account.setActive(EnumStatus.OFFLINE.getCode());
        Account deletedAccount=accountRepository.save(account);
        AccountResponse accountResponse=convertToResponse(deletedAccount);
        return ResponseModel.<AccountResponse>builder().result(accountResponse).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public ResponseModel<AccountResponse> getAccountById(Long accountId) {
        checkAccountById(accountId);
        Account account=accountRepository.findByIdAndActive(accountId,EnumStatus.ONLINE.getCode()).get();
        AccountResponse accountResponse=convertToResponse(account);
        return ResponseModel.<AccountResponse>builder().result(accountResponse).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public boolean checkAccountById(Long accountId){
        if (accountRepository.findByIdAndActive(accountId,EnumStatus.ONLINE.getCode()).isPresent()){
            return true;
        }
        return false;
    }
    @Override
    public boolean checkAccountRequest(AccountRequest accountRequest){
        logger.info("check:" +accountRequest.getAccountNumber());
        if (accountRequest==null){
            return false;
//            throw new MyException(ExceptionEnum.BAD_REQUEST);
        }else if (accountRepository.findByIbanAndActive(accountRequest.getIban(),EnumStatus.ONLINE.getCode())!=null){
             return false;
//            throw new MyException(ExceptionEnum.UNIQUE_IBAN);
        } else if (accountRepository.findByAccountNumberAndActive(accountRequest.getAccountNumber(), EnumStatus.ONLINE.getCode())!=null) {
                   return false;
            //            throw new MyException(ExceptionEnum.UNIQUE_ACCOUNT_NUMBER);
        }
        return true;
    }
    @Override
    public boolean checkCustomerData(Long customerId){
        customerRepository.findByIdAndActive(customerId,EnumStatus.ONLINE.getCode()).orElseThrow(()->new MyException(ExceptionEnum.USER_NOT_FOUND));
        return true;
    }


}
