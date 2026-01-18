package org.groodle.boot.service.user.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.exception.CustomerAlreadyRegisteredException;
import org.groodle.boot.service.user.exception.CustomerNotFoundException;
import org.groodle.boot.service.user.exception.UserNotFoundException;
import org.groodle.boot.service.user.model.User;
import org.groodle.boot.service.user.repository.CustomerRepository;
import org.groodle.boot.service.user.repository.UserRepository;
import org.groodle.boot.service.user.web.vm.*;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

import static org.groodle.boot.service.user.web.vm.Status.SUCCESS;

@Slf4j
@Service
@Transactional
public class UserService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public UserService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public UserCreateResponse create(UserCreateRequest request) {
        userRepository.findById(request.getCustomerId()).ifPresent(user -> {
            throw new CustomerAlreadyRegisteredException(user.getId());
        });
        customerRepository.findAll().forEach(customer -> {log.info("{}", customer);});
        customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));
        User saved = userRepository.save(User.builder().id(request.getCustomerId()).username(request.getUserId()).password(request.getPassword()).build());
        return UserCreateResponse.builder().userId(saved.getUsername()).build();
    }

    public UserRetrieveResponse read(String username) {
        User user = userRepository.findFirstByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return UserRetrieveResponse.builder().userId(user.getUsername()).password(user.getPassword()).build();
    }

    public UserPartialUpdateResponse update(UserPartialUpdateRequest request, String userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        Objects.requireNonNull(request,"UserPartialUpdateRequest cannot be null");
        final User user = userRepository.findFirstByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Optional.ofNullable(request.getNewPassword()).ifPresent(s -> user.setPassword(s));
        Optional.ofNullable(request.getNewUserId()).ifPresent(s -> user.setUsername(s));
        User saved = userRepository.save(user);
        return UserPartialUpdateResponse.builder().userId(saved.getUsername()).build();
    }

    public UserDeleteResponse delete(String userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        userRepository.deleteByUsername(userId);
        return UserDeleteResponse.builder().status(SUCCESS).build();
    }
}
