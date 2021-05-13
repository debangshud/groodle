package org.groodle.boot.service.user.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.exception.CustomerNotFoundException;
import org.groodle.boot.service.user.exception.UserNotFoundException;
import org.groodle.boot.service.user.model.User;
import org.groodle.boot.service.user.repository.CustomerRepository;
import org.groodle.boot.service.user.repository.UserRepository;
import org.groodle.boot.service.user.web.vm.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public UserService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public UserCreateResponse create(@RequestBody UserCreateRequest request) {
        customerRepository.findAll().forEach(customer -> log.info("Id: {}", customer.getId()));
        customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));
        userRepository.save(User.builder().username(request.getUsername()).password(request.getPassword()).build());
        return UserCreateResponse.builder().status("Success").build();
    }

    public UserRetrieveResponse read(@PathVariable String username) {
        User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        return UserRetrieveResponse.builder().username(user.getUsername()).password(user.getPassword()).build();
    }

    public UserPartialUpdateResponse update(@RequestBody UserPartialUpdateRequest request, @PathVariable String username) {
        final User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
        Optional.ofNullable(request.getNewPassword()).ifPresent(s -> user.setPassword(s));
        Optional.ofNullable(request.getNewUsername()).ifPresent(s -> user.setUsername(s));
        userRepository.save(user);
        return UserPartialUpdateResponse.builder().status("Success").build();
    }
}
