package com.example.brestaskk.camunda.delegates.transferDelegates;

import com.example.brestaskk.dto.request.AccountToAccountRequest;
import com.example.brestaskk.service.TransferService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckAccountTransfer")
public class DelegateCheckAccountTransfer implements JavaDelegate {

    private final TransferService transferService;

    public DelegateCheckAccountTransfer(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        AccountToAccountRequest request= (AccountToAccountRequest) delegateExecution
                .getVariable("request");
        delegateExecution.setVariable("checkAccountTransfer",transferService.checkAccountDetail(request));
    }
}
