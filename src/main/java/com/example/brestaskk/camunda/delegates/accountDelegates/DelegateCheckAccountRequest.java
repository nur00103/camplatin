package com.example.brestaskk.camunda.delegates.accountDelegates;

import com.example.brestaskk.dto.request.AccountRequest;
import com.example.brestaskk.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckAccountRequest")
public class DelegateCheckAccountRequest implements JavaDelegate {

    private final AccountService accountService;

    public DelegateCheckAccountRequest(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        AccountRequest accountRequest= (AccountRequest) delegateExecution.getVariable("request");
        delegateExecution.setVariable("checkAccountRequest",accountService.checkAccountRequest(accountRequest));
    }
}
