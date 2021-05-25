package org.groodle.boot.service.user.exception;

public class CustomerAlreadyRegisteredException extends RuntimeException {

    private Long id;

    public CustomerAlreadyRegisteredException(Long id) {
        super();
        this.id = id;
    }
}
