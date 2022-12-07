package com.example.brestaskk.camunda;

import com.example.brestaskk.dto.response.ResponseModel;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Service;

@Service
public class StartCamunda  {
    private final RuntimeService runtimeService;

    public StartCamunda(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public <T> ResponseModel  startBpmn(T data,String bpmnName,Long id){
        VariableMap variableMap=runtimeService.createProcessInstanceByKey(bpmnName)
                .setVariable("id",id)
                .setVariable("request",data)
                .executeWithVariablesInReturn()
                .getVariables();
        return  variableMap.getValue("result", ResponseModel.class);
    }
}
