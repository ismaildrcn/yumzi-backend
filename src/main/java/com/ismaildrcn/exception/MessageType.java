package com.ismaildrcn.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_FOUND("1001", "No record found!"),
    TOKEN_IS_EXPIRED("1002", "The token has expired."),
    USERNAME_NOT_FOUND("1003", "Username not found."),
    USERNAME_OR_PASSWORD_INVALID("1004", "Username or password is invalid."),
    REFRESH_TOKEN_NOT_FOUND("1005", "Refresh token not found."),
    REFRESH_TOKEN_IS_EXPIRED("1006", "The refresh token has expired."),
    GENERAL_EXCEPTION("9999", "An error occurred while processing your request.");

    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
