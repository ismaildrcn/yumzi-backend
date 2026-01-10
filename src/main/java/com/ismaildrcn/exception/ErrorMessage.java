package com.ismaildrcn.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private MessageType messageType;
    private String ofStatic;

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
