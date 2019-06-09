package com.demo.notepad.web.rest.adviser;

import com.demo.notepad.common.FileDidNotLoadException;
import com.demo.notepad.web.rest.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.FileSystemNotFoundException;

@RestControllerAdvice
public class ExceptionHandleRestAdvice {

    private final static String TAG = ExceptionHandleRestAdvice.class.getName();
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandleRestAdvice.class);


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse commonException(Exception ex) {
        logger.error(TAG, ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "UNHANDLED EXCEPTION LOGGED DURING REQUEST");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    @ExceptionHandler(FileDidNotLoadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse fileDidNotLoadException(FileDidNotLoadException ex) {
        logger.error(TAG, ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
    }

    @ExceptionHandler(FileSystemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse fileSystemNotFoundException(FileSystemNotFoundException ex) {
        logger.error(TAG, ex);
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error(TAG, ex.getLocalizedMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND, "Validation failed,");
    }

}
