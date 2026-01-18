package com.ismaildrcn.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {

    private MessageType messageType;
    private String ofStatic;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public ErrorMessage(MessageType messageType) {
        this.messageType = messageType;
    }

    public ErrorMessage(MessageType messageType, String ofStatic) {
        this.messageType = messageType;
        this.ofStatic = ofStatic;
    }

    public ErrorMessage(MessageType messageType, String ofStatic, HttpStatus httpStatus) {
        this.messageType = messageType;
        this.ofStatic = ofStatic;
        this.httpStatus = httpStatus;
    }

    public String prepareErrorMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(messageType.getCode()).append("] - ");
        if (ofStatic != null && !ofStatic.isEmpty()) {
            sb.append(ofStatic).append(" - ");
        }
        sb.append(messageType.getMessage());
        return sb.toString();
    }

}
