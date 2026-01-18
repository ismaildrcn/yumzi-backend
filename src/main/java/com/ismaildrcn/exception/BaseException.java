package com.ismaildrcn.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private HttpStatus httpStatus;

    public BaseException(ErrorMessage message) {
        this.httpStatus = message.getHttpStatus();
        super(message.prepareErrorMessage());
    }
}
