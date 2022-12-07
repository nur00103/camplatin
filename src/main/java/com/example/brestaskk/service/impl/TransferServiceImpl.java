package com.example.brestaskk.service.impl;

import com.example.brestaskk.beans.TransferUtil;
import com.example.brestaskk.dto.request.AccountToAccountRequest;
import com.example.brestaskk.dto.request.CardToCardRequest;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.dto.response.TransferResponse;
import com.example.brestaskk.entity.Account;
import com.example.brestaskk.entity.Card;
import com.example.brestaskk.entity.Transfer;
import com.example.brestaskk.enums.*;
import com.example.brestaskk.repository.AccountRepository;
import com.example.brestaskk.repository.CardRepository;
import com.example.brestaskk.repository.TransferRepository;
import com.example.brestaskk.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    public Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    private final TransferRepository transferRepository;
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseModel<TransferResponse> cardToCardTransfer(CardToCardRequest cardToCardRequest) {
        TransferUtil transferUtil=setBalanceToCardAndAccount(cardToCardRequest);
        accountRepository.save(transferUtil.getCreditorAccount());
        accountRepository.save(transferUtil.getDebitorAccount());

        Transfer savedTransfer=saveTransfer(transferUtil.getCreditorAmount(),transferUtil.getDebitorAmount(),transferUtil.getCreditorCard()
                ,transferUtil.getDebitorCard(),transferUtil.getCreditorAccount(),transferUtil.getDebitorAccount());
        return ResponseModel.<TransferResponse>builder().result(
                        modelMapper.map(savedTransfer, TransferResponse.class)).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }
    public Transfer saveTransfer(BigDecimal creditorAmount,BigDecimal debitorAmount, Card creditorCard, Card debitorCard, Account creditorAccount, Account debitorAccount){
        Transfer transfer = new Transfer().setCreditorAmount(creditorAmount)
                .setDebitorAmount(debitorAmount)
                .setCreditorCardID(creditorCard.getId()).setDebitorCardID(debitorCard.getId())
                .setDebitorAccountID(debitorAccount.getId()).setCreditorAccountID(creditorAccount.getId())
                .setCreditorCurrency(creditorAccount.getCurrency()).setDebitorCurrency(debitorAccount.getCurrency())
                .setStatus(TransferStatusEnum.SUCCESS).setTransferType(TransferTypeEnum.CART_TO_CART);
        Transfer savedTransfer = transferRepository.save(transfer);
        return savedTransfer;
    }

    @Override
    public boolean checkCardDetails(CardToCardRequest cardToCardRequest) {
        if (!cardRepository.findByIdAndActive(cardToCardRequest.getCreditorCardID(), EnumStatus.ONLINE.getCode()).isPresent()) {
            return false;
        } else if (!cardRepository.findByIdAndActive(cardToCardRequest.getDebitorCardID(), EnumStatus.ONLINE.getCode()).isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public ResponseModel<List<TransferResponse>> getAllTransfer() {
        List<Transfer> transferList = transferRepository.findAll();
        List<TransferResponse> transferResponses = transferList.stream()
                .map(transfer -> convertToResponse(transfer)).collect(Collectors.toList());
        return ResponseModel.<List<TransferResponse>>builder().result(transferResponses).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }
    @Override
    public boolean checkBalance(CardToCardRequest cardToCardRequest) {
        Card creditorCard = cardRepository.findByIdAndActive(cardToCardRequest.getCreditorCardID(), EnumStatus.ONLINE.getCode()).get();
        if (cardToCardRequest.getAmount().compareTo(creditorCard.getAccount().getBalance()) == 1) {
            return false;
        }
        return true;
    }

    @Override
    public ResponseModel<TransferResponse> accountToAccount(AccountToAccountRequest request) {
        Account creditor=accountRepository.findByIdAndActive(request.getCreditorAccountID(),EnumStatus.ONLINE.getCode()).get();
        Account debitor=accountRepository.findByIdAndActive(request.getDebitorAccountID(),EnumStatus.ONLINE.getCode()).get();

        creditor.setBalance(creditor.getBalance().subtract(request.getCreditorAmount()));
        debitor.setBalance(debitor.getBalance().add(request.getCreditorAmount()));

        accountRepository.save(creditor);
        accountRepository.save(debitor);

        Transfer transfer = new Transfer().setCreditorAmount(request.getCreditorAmount())
                .setDebitorAmount(request.getCreditorAmount())
                .setDebitorAccountID(debitor.getId()).setCreditorAccountID(creditor.getId())
                .setCreditorCurrency(creditor.getCurrency()).setDebitorCurrency(debitor.getCurrency())
                .setStatus(TransferStatusEnum.SUCCESS).setTransferType(TransferTypeEnum.ACCOUNT_TO_ACCOUNT);
        Transfer savedTransfer = transferRepository.save(transfer);
        return ResponseModel.<TransferResponse>builder().result(
                        modelMapper.map(savedTransfer, TransferResponse.class)).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    @Override
    public boolean checkBalanceAccount(AccountToAccountRequest request) {
        Account creditor=accountRepository.findByIdAndActive(request.getCreditorAccountID(),EnumStatus.ONLINE.getCode()).get();
        if (creditor.getBalance().compareTo(request.getCreditorAmount())==-1){
            return false;
        }return true;
    }

    @Override
    public boolean checkAccountDetail(AccountToAccountRequest request) {
        if (!accountRepository.findByIdAndActive(request.getCreditorAccountID(),EnumStatus.ONLINE.getCode()).isPresent()){
            return false;
        }else if (!accountRepository.findByIdAndActive(request.getDebitorAccountID(),EnumStatus.ONLINE.getCode()).isPresent()){
            return false;
        }
        return true;
    }

    @Override
    public TransferUtil setBalanceToCardAndAccount(CardToCardRequest cardToCardRequest) {
        Card creditorCard = cardRepository.findByIdAndActive(cardToCardRequest.getCreditorCardID()
                , EnumStatus.ONLINE.getCode()).get();
        Account creditorAccount = accountRepository.findByIdAndActive(creditorCard.getAccount().getId()
                , EnumStatus.ONLINE.getCode()).get();
        Card debitorCard = cardRepository.findByIdAndActive(cardToCardRequest.getDebitorCardID()
                , EnumStatus.ONLINE.getCode()).get();
        Account debitorAccount = accountRepository.findByIdAndActive(debitorCard.getAccount().getId()
                , EnumStatus.ONLINE.getCode()).get();
        BigDecimal creditorBalance=cardToCardRequest.getAmount();
        BigDecimal debitorBalance=cardToCardRequest.getAmount();
        if ((creditorAccount.getCurrency().toString()=="AZN")
                &&(debitorAccount.getCurrency().toString()=="USD")){
            debitorBalance=creditorBalance.divide( BigDecimal.valueOf(1.70));
        }else if ((creditorAccount.getCurrency().toString()=="USD")
                &&(debitorAccount.getCurrency().toString()=="AZN")){
            debitorBalance=creditorBalance.multiply(BigDecimal.valueOf(1.70));
        }
        creditorAccount.setBalance(creditorAccount.getBalance()
                .subtract(creditorBalance));
        debitorAccount.setBalance(debitorAccount.getBalance()
                .add(debitorBalance));
        TransferUtil transferUtil=new TransferUtil().setCreditorCard(creditorCard)
                .setDebitorCard(debitorCard).setCreditorAccount(creditorAccount)
                .setDebitorAccount(debitorAccount).setCreditorAmount(creditorBalance).setDebitorAmount(debitorBalance);
        return transferUtil;
    }

    public TransferResponse convertToResponse(Transfer transfer) {
        return modelMapper.map(transfer, TransferResponse.class);
    }


}
