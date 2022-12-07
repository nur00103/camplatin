package com.example.brestaskk.controller;

import com.example.brestaskk.camunda.StartCamunda;
import com.example.brestaskk.dto.request.AccountPutRequest;
import com.example.brestaskk.dto.request.AccountRequest;
import com.example.brestaskk.dto.response.AccountResponse;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final StartCamunda startCamunda;

    @GetMapping
    public ResponseModel<List<AccountResponse>> getAllAccount(){
        return startCamunda.startBpmn(null,"getAccount",null);
    }

    @GetMapping("/{accountId}")
    public ResponseModel<AccountResponse> getAccountById(@PathVariable Long accountId){
        return startCamunda.startBpmn(null,"getAccountById",accountId);
    }

    @PostMapping("/add")
    public ResponseModel<AccountResponse> addAccount(@RequestBody @Valid AccountRequest accountRequest){
        return startCamunda.startBpmn(accountRequest,"postAccount",null);
    }
    @PutMapping("/{accountId}")
    public ResponseModel<AccountResponse> updateAccount(@PathVariable Long accountId, @RequestBody @Valid AccountPutRequest accountRequest){
        return startCamunda.startBpmn(accountRequest,"putAccount",accountId);
    }
    @DeleteMapping("/{accountId}")
    public ResponseModel<AccountResponse> deleteAccount(@PathVariable Long accountId){
        return accountService.deleteAccount(accountId);
    }
}
