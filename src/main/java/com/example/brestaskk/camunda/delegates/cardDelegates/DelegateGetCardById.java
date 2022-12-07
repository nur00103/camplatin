package com.example.brestaskk.camunda.delegates.cardDelegates;

import com.example.brestaskk.service.CardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateGetCardById")
public class DelegateGetCardById implements JavaDelegate {

    private final CardService cardService;

    public DelegateGetCardById(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long cardId=(Long)delegateExecution.getVariable("id");
        delegateExecution.setVariable("result",cardService.getCardById(cardId));
    }
}
