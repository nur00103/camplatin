package com.example.brestaskk.camunda.delegates.cardDelegates;

import com.example.brestaskk.service.CardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateGetAllCard")
public class DelegateGetAllCard implements JavaDelegate {
    private final CardService cardService;

    public DelegateGetAllCard(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("result",cardService.getAllCard());
    }
}
