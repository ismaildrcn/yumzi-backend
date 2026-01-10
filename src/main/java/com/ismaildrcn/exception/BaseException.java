package com.ismaildrcn.exception;

public class BaseException extends RuntimeException {

    public BaseException(ErrorMessage message) {
        super(message.prepareErrorMessage());
    }
}
