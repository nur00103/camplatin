package com.example.brestaskk.camunda.delegates.cardDelegates;

import com.example.brestaskk.dto.request.CardPutRequest;
import com.example.brestaskk.service.CardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegatePutCard")
public class DelegatePutCard implements JavaDelegate {
    private final CardService cardService;

    public DelegatePutCard(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CardPutRequest cardPutRequest= (CardPutRequest) delegateExecution.getVariable("request");
        Long cardId=(Long)delegateExecution.getVariable("id") ;
        delegateExecution.setVariable("result",cardService.updateCard(cardId,cardPutRequest));
    }
}
