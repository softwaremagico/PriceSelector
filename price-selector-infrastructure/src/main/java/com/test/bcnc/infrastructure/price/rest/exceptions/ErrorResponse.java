package com.test.bcnc.infrastructure.price.rest.exceptions;

import java.io.Serial;
import java.io.Serializable;

/**
 * Method for returning error information to a frontend or another external service.
 */
public class ErrorResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -257991215240650750L;
    private final String message;
    private final String code;

    public ErrorResponse(String message) {
        this(message, null);
    }

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        if (code != null) {
            return code;
        }
        if (message != null) {
            return message.replace(" ", "_").toLowerCase();
        }
        return null;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
