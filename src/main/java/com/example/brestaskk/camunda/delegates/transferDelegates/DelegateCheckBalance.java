package com.example.brestaskk.camunda.delegates.transferDelegates;

import com.example.brestaskk.dto.request.CardToCardRequest;
import com.example.brestaskk.service.TransferService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckBalance")
public class DelegateCheckBalance implements JavaDelegate {

    private final TransferService transferService;

    public DelegateCheckBalance(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CardToCardRequest cardToCardRequest= (CardToCardRequest) delegateExecution.getVariable("request");
        delegateExecution.setVariable("checkBalance",transferService.checkBalance(cardToCardRequest));
    }
}
