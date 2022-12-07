package com.example.brestaskk.camunda.delegates.transferDelegates;

import com.example.brestaskk.dto.request.CardToCardRequest;
import com.example.brestaskk.service.TransferService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCardToCard")
public class DelegateCardToCard implements JavaDelegate {

    private final TransferService transferService;

    public DelegateCardToCard(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CardToCardRequest cardToCardRequest= (CardToCardRequest) delegateExecution.getVariable("request");
        delegateExecution.setVariable("result",transferService.cardToCardTransfer(cardToCardRequest));
    }
}
