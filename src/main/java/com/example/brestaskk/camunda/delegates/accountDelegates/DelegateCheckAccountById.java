package com.example.brestaskk.camunda.delegates.accountDelegates;

import com.example.brestaskk.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckAccountById")
public class DelegateCheckAccountById implements JavaDelegate {
    private final AccountService accountService;

    public DelegateCheckAccountById(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long accountId=(Long) delegateExecution.getVariable("id");
        delegateExecution.setVariable("checkAccountById",accountService.checkAccountById(accountId));
    }
}
