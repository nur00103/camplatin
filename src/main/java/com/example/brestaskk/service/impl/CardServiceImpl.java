package com.example.brestaskk.service.impl;

import com.example.brestaskk.dto.request.CardPutRequest;
import com.example.brestaskk.dto.request.CardRequest;
import com.example.brestaskk.dto.response.CardResponse;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.entity.Account;
import com.example.brestaskk.entity.Card;
import com.example.brestaskk.enums.EnumStatus;
import com.example.brestaskk.enums.ExceptionEnum;
import com.example.brestaskk.exception.MyException;
import com.example.brestaskk.repository.AccountRepository;
import com.example.brestaskk.repository.CardRepository;
import com.example.brestaskk.service.CardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    Logger logger= LoggerFactory.getLogger(CardServiceImpl.class);
    private static final Long THREE_YEARS = (long) (3 * 365 * 24 * 60 * 60);
    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;
    @Override
    public ResponseModel<List<CardResponse>> getAllCard() {
        List<Card> cardList=cardRepository.findAllByActive(EnumStatus.ONLINE.getCode());
        if (cardList.isEmpty()){
            throw new MyException(ExceptionEnum.CARD_NOT_FOUND);
        }
        List<CardResponse> cardResponseList=cardList.stream().map(card -> convertToResponse(card)).collect(Collectors.toList());
        return ResponseModel.<List<CardResponse>>builder().result(cardResponseList).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public Card convertToEntity(CardRequest cardRequest) {
        Card card=modelMapper.map(cardRequest,Card.class);
        return card;
    }

    @Override
    public CardResponse convertToResponse(Card card) {
        Account account=accountRepository.findByIdAndActive(card.getAccount().getId(),EnumStatus.ONLINE.getCode()).get();
        CardResponse cardResponse=modelMapper.map(card,CardResponse.class);
        cardResponse.setBalance(account.getBalance());
        return cardResponse;
    }

    @Override
    public ResponseModel<CardResponse> getCardById(Long cardId) {
        Card card=cardRepository.findByIdAndActive(cardId,EnumStatus.ONLINE.getCode()).get();
        CardResponse cardResponse=convertToResponse(card);
        return ResponseModel.<CardResponse>builder().result(cardResponse).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public boolean checkCardById(Long cardId) {
        if (cardRepository.findByIdAndActive(cardId,EnumStatus.ONLINE.getCode()).isPresent()){
            return true;
        }else {
        return false;
        }
    }

    @Override
    public ResponseModel<CardResponse> addCard(CardRequest cardRequest) {
        Card card=convertToEntity(cardRequest);
        card.setExpireDate(Date.from(Instant.now().plusSeconds(THREE_YEARS)));
        Card savedCard=cardRepository.save(card);
        CardResponse cardResponse=convertToResponse(savedCard);
        return ResponseModel.<CardResponse>builder().result(cardResponse).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public boolean checkAccountById(Long accountId) {
        if (accountRepository.findByIdAndActive(accountId,EnumStatus.ONLINE.getCode()).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public ResponseModel<CardResponse> updateCard(Long cardId, CardPutRequest cardRequest) {
        Card card=cardRepository.findByIdAndActive(cardId,EnumStatus.ONLINE.getCode()).get();
        card.setStatus(cardRequest.getStatus());
        card.setId(cardId);
        Card updatedCard=cardRepository.save(card);
        CardResponse cardResponse=convertToResponse(updatedCard);
        return ResponseModel.<CardResponse>builder().result(cardResponse).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public ResponseModel<CardResponse> deleteCard(Long cardId) {
        checkCardById(cardId);
        Card card=cardRepository.findByIdAndActive(cardId,EnumStatus.ONLINE.getCode()).get();
        card.setActive(EnumStatus.OFFLINE.getCode());
        Card deletedCard=cardRepository.save(card);
        CardResponse cardResponse=convertToResponse(deletedCard);
        return ResponseModel.<CardResponse>builder().result(cardResponse).status(ExceptionEnum.SUCCESS.getMessage())
                .code(ExceptionEnum.SUCCESS.getCode()).error(false).build();
    }

    @Override
    public boolean checkCardNumber(Long cardNumber) {
        if (cardRepository.findByCardNumberAndActive(cardNumber,EnumStatus.ONLINE.getCode()).isPresent()){
            return false;
        }else {
            return true;
        }
    }

//
    }

