package com.example.brestaskk.camunda.delegates.accountDelegates;

import com.example.brestaskk.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateGetAllAccount")
public class DelegateGetAllAccount implements JavaDelegate {
    private final AccountService accountService;

    public DelegateGetAllAccount(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("result",accountService.getAllAccount());
    }
}
