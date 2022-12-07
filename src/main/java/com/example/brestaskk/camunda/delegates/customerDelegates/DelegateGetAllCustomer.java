package com.example.brestaskk.camunda.delegates.customerDelegates;

import com.example.brestaskk.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateGetAllCustomer")
public class DelegateGetAllCustomer implements JavaDelegate {

    private final CustomerService customerService;

    public DelegateGetAllCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("result",customerService.getAllCustomer());
    }
}
