package com.example.brestaskk.camunda.delegates.customerDelegates;

import com.example.brestaskk.dto.request.CustomerRequest;
import com.example.brestaskk.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckData")
public class DelegateCheckData implements JavaDelegate {

    private final CustomerService customerService;

    public DelegateCheckData(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CustomerRequest customerRequest= (CustomerRequest) delegateExecution.getVariable("request");
        Long customerId= (Long) delegateExecution.getVariable("id");
        Boolean test=customerService.checkCustomerData(customerId);
        delegateExecution.setVariable("checkData",test);
    }
}
