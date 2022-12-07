package com.example.brestaskk.service;

import com.example.brestaskk.beans.TransferUtil;
import com.example.brestaskk.dto.request.AccountToAccountRequest;
import com.example.brestaskk.dto.request.CardToCardRequest;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.dto.response.TransferResponse;

import java.util.List;

public interface TransferService {
    ResponseModel<TransferResponse> cardToCardTransfer(CardToCardRequest cardToCardRequest);

    public boolean checkCardDetails(CardToCardRequest cardToCardRequest);

    ResponseModel<List<TransferResponse>> getAllTransfer();
    public boolean checkBalance(CardToCardRequest cardToCardRequest);
    ResponseModel<TransferResponse> accountToAccount(AccountToAccountRequest request);

    public boolean checkBalanceAccount(AccountToAccountRequest request);

    public boolean checkAccountDetail(AccountToAccountRequest request);

    public TransferUtil setBalanceToCardAndAccount(CardToCardRequest cardToCardRequest);
}
