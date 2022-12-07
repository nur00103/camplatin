package com.example.brestaskk.exception;
import com.example.brestaskk.dto.response.CustomerResponse;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.enums.ExceptionEnum;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel<CustomerResponse> expMy(MyException myException){
        return ResponseModel.<CustomerResponse>builder().status(myException.getMessage())
                .code(myException.getCode()).result(null).error(true).build();
    }
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel<CustomerResponse> expInvalidMy(MyException myException){
        return ResponseModel.<CustomerResponse>builder().status("This type is not exist in database")
                .code(myException.getCode()).result(null).error(true).build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ResponseModel<CustomerResponse> expReq(MethodArgumentTypeMismatchException e){
        return ResponseModel.<CustomerResponse>builder().status(ExceptionEnum.INPUT.getMessage())
                .code(ExceptionEnum.INPUT.getCode()).result(null).error(true).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel<CustomerResponse> exceptionInput(BindingResult bindingResult){
        final String[] error = {""};
        bindingResult.getFieldErrors()
                .stream()
                .forEach(fieldError -> {
                    error[0] = fieldError.getField()+" " + fieldError.getDefaultMessage() ;
                });
        return ResponseModel.<CustomerResponse>builder().status(error[0])
                .code(ExceptionEnum.BAD_REQUEST.getCode()).result(null).error(true).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseModel<CustomerResponse> exceptionAll(Exception e){
        return ResponseModel.<CustomerResponse>builder().status(e.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value()).result(null).error(true).build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseModel<CustomerResponse> exceptionAllRunTime(RuntimeException e){
        return ResponseModel.<CustomerResponse>builder().status(e.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value()).result(null).error(true).build();
    }
}
