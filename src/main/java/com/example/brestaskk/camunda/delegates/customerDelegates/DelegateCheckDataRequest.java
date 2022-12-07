package com.example.brestaskk.camunda.delegates.customerDelegates;

import com.example.brestaskk.dto.request.CustomerRequest;
import com.example.brestaskk.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckDataRequest")
public class DelegateCheckDataRequest implements JavaDelegate {

    private final CustomerService customerService;

    public DelegateCheckDataRequest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CustomerRequest customerRequest= (CustomerRequest) delegateExecution.getVariable("request");
        delegateExecution.setVariable("checkDataRequest",customerService.checkCustomerDataRequest(customerRequest));
    }
}
