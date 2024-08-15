package com.ennaru.practice.jpa;

import com.ennaru.practice.common.domain.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus
    public ResponseEntity<BaseResponse> error404() {

        BaseResponse baseResponse = BaseResponse.builder()
                .result_code("1000")
                .result_message("요청 정보가 올바르지 않습니다.")
                .build();

        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }
}
