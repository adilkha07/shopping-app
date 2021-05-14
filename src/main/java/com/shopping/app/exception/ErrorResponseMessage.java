package com.shopping.app.exception;

import java.time.OffsetDateTime;

import lombok.Value;

/**
 * api error response
 *
 */
@Value
public class ErrorResponseMessage {
    private int code;
    private String message;
    private OffsetDateTime timeStamp;
}
