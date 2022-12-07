package com.example.brestaskk.controller;

import com.example.brestaskk.camunda.StartCamunda;
import com.example.brestaskk.dto.request.AccountToAccountRequest;
import com.example.brestaskk.dto.request.CardToCardRequest;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.dto.response.TransferResponse;
import com.example.brestaskk.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;
    private final StartCamunda startCamunda;

    @GetMapping
    public ResponseModel<List<TransferResponse>> getAllTransfer(){
        return startCamunda.startBpmn(null,"getAllTransfer",null);
    }

    @PostMapping("/cardToCard")
    public ResponseModel<TransferResponse> cardToCardTransfer(@RequestBody @Valid CardToCardRequest cardToCardRequest){
        return startCamunda.startBpmn(cardToCardRequest,"cardToCard",null);
    }
    @PostMapping("/accountToAccount")
    public ResponseModel<TransferResponse> accountToAccountTransfer(@RequestBody @Valid AccountToAccountRequest request){
        return startCamunda.startBpmn(request,"accountToAccount",null);
    }


}
