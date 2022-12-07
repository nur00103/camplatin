package com.example.brestaskk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseModel<T> implements Serializable {
    private T result;
    private Boolean error;
    private String status;
    private int code;
}
