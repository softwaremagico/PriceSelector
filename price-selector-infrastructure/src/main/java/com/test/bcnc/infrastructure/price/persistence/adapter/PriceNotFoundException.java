package com.test.bcnc.infrastructure.price.persistence.adapter;

import com.test.bcnc.logger.exceptions.LoggedException;

import java.io.Serial;

public class PriceNotFoundException extends LoggedException {
    @Serial
    private static final long serialVersionUID = -1823063060430430584L;

    public PriceNotFoundException(Class<?> clazz, String message) {
        super(clazz, message);
    }
}
