package org.groodle.boot.service.user.web.rest;

import org.groodle.boot.service.user.exception.CustomerNotFoundException;
import org.groodle.boot.service.user.exception.IncorrectPasswordException;
import org.groodle.boot.service.user.web.vm.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserSessionControllerAdvice {

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handle(CustomerNotFoundException e, WebRequest request) {
        return ErrorMessage.builder().message("Incorrect password supplied").timestamp(LocalDateTime.now()).build();
    }
}
