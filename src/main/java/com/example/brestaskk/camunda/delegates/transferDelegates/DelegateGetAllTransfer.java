package com.example.brestaskk.camunda.delegates.transferDelegates;

import com.example.brestaskk.service.TransferService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateGetAllTransfer")
public class DelegateGetAllTransfer implements JavaDelegate {
    private final TransferService transferService;

    public DelegateGetAllTransfer(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("result",transferService.getAllTransfer());
    }
}
