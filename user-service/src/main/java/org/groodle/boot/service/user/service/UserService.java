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
        customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));
        User saved = userRepository.save(User.builder().id(request.getCustomerId()).username(request.getUserId()).password(request.getPassword()).build());
        return UserCreateResponse.builder().userId(saved.getUsername()).build();
    }

    public UserRetrieveResponse read(@PathVariable String username) {
        User user = userRepository.findFirstByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return UserRetrieveResponse.builder().userId(user.getUsername()).password(user.getPassword()).build();
    }

    public UserPartialUpdateResponse update(@RequestBody UserPartialUpdateRequest request, @PathVariable String userId) {
        final User user = userRepository.findFirstByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Optional.ofNullable(request.getNewPassword()).ifPresent(s -> user.setPassword(s));
        Optional.ofNullable(request.getNewUserId()).ifPresent(s -> user.setUsername(s));
        User saved = userRepository.save(user);
        return UserPartialUpdateResponse.builder().userId(saved.getUsername()).build();
    }
}
