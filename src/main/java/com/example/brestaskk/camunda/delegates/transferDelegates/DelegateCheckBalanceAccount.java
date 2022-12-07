package com.example.brestaskk.camunda.delegates.transferDelegates;

import com.example.brestaskk.dto.request.AccountToAccountRequest;
import com.example.brestaskk.service.TransferService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckBalanceAccount")
public class DelegateCheckBalanceAccount implements JavaDelegate {
    private final TransferService transferService;

    public DelegateCheckBalanceAccount(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        AccountToAccountRequest request= (AccountToAccountRequest) delegateExecution
                .getVariable("request");
        delegateExecution.setVariable("checkBalanceAccount",transferService.checkBalanceAccount(request));
    }
}
