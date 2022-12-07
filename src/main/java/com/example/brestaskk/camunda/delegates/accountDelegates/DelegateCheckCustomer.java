package com.example.brestaskk.camunda.delegates.accountDelegates;

import com.example.brestaskk.dto.request.AccountRequest;
import com.example.brestaskk.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckCustomer")
public class DelegateCheckCustomer implements JavaDelegate {
    private final AccountService accountService;

    public DelegateCheckCustomer(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        AccountRequest accountRequest= (AccountRequest) delegateExecution.getVariable("request");
        Long customerId= accountRequest.getCustomerId();
        delegateExecution.setVariable("checkCustomer",accountService.checkCustomerData(customerId));
    }
}
