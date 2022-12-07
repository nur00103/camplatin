package com.example.brestaskk.camunda.delegates.customerDelegates;

import com.example.brestaskk.dto.request.CustomerRequest;
import com.example.brestaskk.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegatePutCustomer")
public class DelegatePutCustomer implements JavaDelegate {

    private final CustomerService customerService;

    public DelegatePutCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //TODO Customer request
        CustomerRequest customerRequest= (CustomerRequest) delegateExecution.getVariable("request");
        Long customerId= (Long) delegateExecution.getVariable("id");
        delegateExecution.setVariable("result",customerService.updateCustomer(customerId,customerRequest));
    }
}
