package com.test.bcnc.infrastructure.price.rest.exceptions;

import com.test.bcnc.application.exceptions.PriceNotFoundException;
import com.test.bcnc.logger.PriceLogger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Converts exceptions into HTTP error codes and adds some extra information about the error.
 */
@ControllerAdvice
public class PriceSelectorExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        PriceLogger.errorMessage(this.getClass(), ex);
        return new ResponseEntity<>(new ErrorResponse("MESSAGE_NOT_READABLE", ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PriceNotFoundException.class)
    protected ResponseEntity<Object> priceNotFoundException(PriceNotFoundException ex) {
        PriceLogger.errorMessage(this.getClass(), ex);
        return new ResponseEntity<>(new ErrorResponse("NOT_FOUND", "element_not_found", ex), HttpStatus.NOT_FOUND);
    }
}
