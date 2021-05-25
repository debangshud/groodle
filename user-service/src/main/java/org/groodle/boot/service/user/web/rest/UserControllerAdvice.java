package org.groodle.boot.service.user.web.rest;

import org.groodle.boot.service.user.exception.CustomerNotFoundException;
import org.groodle.boot.service.user.exception.CustomerAlreadyRegisteredException;
import org.groodle.boot.service.user.exception.UserNotFoundException;
import org.groodle.boot.service.user.web.vm.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handle(CustomerNotFoundException e, WebRequest request) {
        return ErrorMessage.builder().message("Customer not found").timestamp(LocalDateTime.now()).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handle(UserNotFoundException e, WebRequest request) {
        return ErrorMessage.builder().message("User not found").timestamp(LocalDateTime.now()).build();
    }

    @ExceptionHandler(CustomerAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handle(CustomerAlreadyRegisteredException e, WebRequest request) {
        return ErrorMessage.builder().message("Customer already registered").timestamp(LocalDateTime.now()).build();
    }
}
