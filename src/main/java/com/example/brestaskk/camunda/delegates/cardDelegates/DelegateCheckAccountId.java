package com.example.brestaskk.camunda.delegates.cardDelegates;

import com.example.brestaskk.dto.request.CardRequest;
import com.example.brestaskk.service.CardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckAccountId")
public class DelegateCheckAccountId implements JavaDelegate {

    private final CardService cardService;

    public DelegateCheckAccountId(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CardRequest cardRequest= (CardRequest) delegateExecution.getVariable("request");
        delegateExecution.setVariable("checkAccountId",cardService.checkAccountById(cardRequest.getAccountId()));

    }
}
