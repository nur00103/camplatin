package com.example.brestaskk.camunda.delegates.accountDelegates;

import com.example.brestaskk.dto.request.AccountPutRequest;
import com.example.brestaskk.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegatePutAccount")
public class DelegatePutAccount implements JavaDelegate {
    private final AccountService accountService;

    public DelegatePutAccount(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long accountId= (Long) delegateExecution.getVariable("id");
        AccountPutRequest accountPutRequest= (AccountPutRequest) delegateExecution.getVariable("request");
        delegateExecution.setVariable("result",accountService.updateAccount(accountId,accountPutRequest));
    }
}
