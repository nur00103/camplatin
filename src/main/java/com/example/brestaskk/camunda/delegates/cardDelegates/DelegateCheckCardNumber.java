package com.example.brestaskk.camunda.delegates.cardDelegates;

import com.example.brestaskk.dto.request.CardRequest;
import com.example.brestaskk.service.CardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckCardNumber")
public class DelegateCheckCardNumber implements JavaDelegate {

    private final CardService cardService;

    public DelegateCheckCardNumber(CardService cardService) {
        this.cardService = cardService;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CardRequest cardRequest= (CardRequest)delegateExecution.getVariable("request");
        delegateExecution.setVariable("checkCardNumber",cardService.checkCardNumber(cardRequest.getCardNumber()));
    }
}
