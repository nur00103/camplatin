package com.example.brestaskk.camunda.delegates.cardDelegates;

import com.example.brestaskk.service.CardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckCardById")
public class DelegateCheckCardById implements JavaDelegate {
    private final CardService cardService;

    public DelegateCheckCardById(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long cardId=(Long)delegateExecution.getVariable("id");
        delegateExecution.setVariable("checkCardById",cardService.checkCardById(cardId));
    }
}
