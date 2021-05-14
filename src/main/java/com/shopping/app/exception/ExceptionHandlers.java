package com.shopping.app.exception;

import java.time.OffsetDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles all api exceptions
 *
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlers {
    /**
     * handle MethodArgumentNotValidException,HttpMessageNotReadableException,DataIntegrityViolationException
     * @param request HttpServletRequest
     * @param e exception
     * @return ResponseEntity
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class,
            DataIntegrityViolationException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponseMessage> handleBadInputDataExceptions(final HttpServletRequest request, final Exception e) {
        log.error("Bad request exception {} ", e);
        final ErrorResponseMessage error = new ErrorResponseMessage(HttpStatus.BAD_REQUEST.value(),
                ErrorMessages.BAD_REQUEST_MSG,
                OffsetDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * handle BadRequestException
     * @param request HttpServletRequest
     * @param e exception
     * @return ResponseEntity
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseMessage> handleBadRequestException(
            final HttpServletRequest request,
            final BadRequestException e) {
        log.error("Bad request exception {} ", e);
        final ErrorResponseMessage error = new ErrorResponseMessage(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                OffsetDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * handle HttpRequestMethodNotSupportedException
     * @param request HttpServletRequest
     * @param ex1 HttpRequestMethodNotSupportedException
     * @return ResponseEntity
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseMessage> handleHttpRequestMethodNotSupportedException(
            final HttpRequestMethodNotSupportedException ex1) {
        final ErrorResponseMessage error = new ErrorResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(),
                ErrorMessages.METHOD_NOT_ALLOWES_MSG,
                OffsetDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * handle HttpMediaTypeNotSupportedException
     * @param ex1 HttpMediaTypeNotSupportedException
     * @return ResponseEntity
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponseMessage> handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException ex1) {
        final ErrorResponseMessage error = new ErrorResponseMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                ErrorMessages.UNSUPPORTED_MEDIA_TYPE_MSG,
                OffsetDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * handle unknown exceptions
     * @param request HttpServletRequest
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponseMessage> handleException(final HttpServletRequest request, final Exception e) {
        log.error("Unhandled Exception {} ", e);
        final ErrorResponseMessage error = new ErrorResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorMessages.INTERNAL_SERVER_ERROR_MSG,
                OffsetDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
