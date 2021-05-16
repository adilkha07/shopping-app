package com.shopping.app.exception;

/**
 * Custom bad request exception
 *
 */
public class BadRequestException extends RuntimeException {
    /**
     * BadRequestException
     * @param message input custom message
     */
    public BadRequestException(final String message) {
        super(message);
    }
}
