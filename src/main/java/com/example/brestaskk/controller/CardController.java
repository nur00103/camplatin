package com.example.brestaskk.controller;

import com.example.brestaskk.camunda.StartCamunda;
import com.example.brestaskk.dto.request.CardPutRequest;
import com.example.brestaskk.dto.request.CardRequest;
import com.example.brestaskk.dto.response.CardResponse;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final StartCamunda startCamunda;

    @GetMapping
    public ResponseModel<List<CardResponse>> getAllCard(){
        return startCamunda.startBpmn(null,"getAllCard",null);
    }
    @GetMapping("/{cardId}")
    public ResponseModel<CardResponse> getCardById(@PathVariable Long cardId){
        return startCamunda.startBpmn(null,"getByIdCard",cardId);
    }
    @PostMapping("/add")
    public ResponseModel<CardResponse> addCard(@RequestBody @Valid CardRequest cardRequest){
        return startCamunda.startBpmn(cardRequest,"addCard",null);
    }
    @PutMapping("/{cardId}")
    public ResponseModel<CardResponse>updateCard(@PathVariable Long cardId,@Valid @RequestBody CardPutRequest cardRequest){
        return startCamunda.startBpmn(cardRequest,"putCard",cardId);
    }
    @DeleteMapping("/{cardId}")
    public ResponseModel<CardResponse> deleteCard(@PathVariable Long cardId){
        return cardService.deleteCard(cardId);
    }

}
