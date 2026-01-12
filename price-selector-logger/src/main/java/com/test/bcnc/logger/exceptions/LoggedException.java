package com.test.bcnc.logger.exceptions;

import com.test.bcnc.logger.PriceLogger;

import java.io.Serial;

/**
 * An exception that is automatically added to the logger.
 */
public abstract class LoggedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -697806165140782399L;

    protected LoggedException(Class<?> clazz, String message) {
        PriceLogger.severe(clazz.getName(), message);
    }
}
