package com.example.brestaskk.camunda.delegates.customerDelegates;

import com.example.brestaskk.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateGetCustomerById")
public class DelegateGetCustomerById implements JavaDelegate {

    private final CustomerService customerService;

    public DelegateGetCustomerById(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
    Long customerId=(Long)delegateExecution.getVariable("id");
    delegateExecution.setVariable("result",customerService.getCustomerById(customerId));
    }
}
