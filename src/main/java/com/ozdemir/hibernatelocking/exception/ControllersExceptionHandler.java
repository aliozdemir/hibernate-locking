package com.ozdemir.hibernatelocking.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.hql.internal.ast.ErrorReporter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllersExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserExistException(UserExistException ex){
        log.info(ex.getMessage());
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return response;
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotExistException(UserNotExistException ex){
        log.info(ex.getMessage());
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return response;
    }


}
