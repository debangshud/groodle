package org.groodle.boot.service.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.exception.CustomerNotFoundException;
import org.groodle.boot.service.user.exception.IncorrectPasswordException;
import org.groodle.boot.service.user.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
@Slf4j
@RestControllerAdvice
public class UserSessionControllerAdvice {

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handle(CustomerNotFoundException e, WebRequest request) {
        log.error("Incorrect password supplied", e);
        log.info("Handling incorrect password exception: {}",request);
        return ErrorMessage.builder().message("Incorrect password supplied").timestamp(LocalDateTime.now()).build();
    }
}
