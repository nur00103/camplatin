package com.example.brestaskk.camunda.delegates.accountDelegates;

import com.example.brestaskk.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateGetByIdAccount")
public class DelegateGetByIdAccount implements JavaDelegate {

    private final AccountService accountService;

    public DelegateGetByIdAccount(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long accountId=(Long) delegateExecution.getVariable("id");
        delegateExecution.setVariable("result",accountService.getAccountById(accountId));
    }
}
