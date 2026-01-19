package org.groodle.boot.service.user.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.dto.UserDto;
import org.groodle.boot.service.user.exception.CustomerAlreadyRegisteredException;
import org.groodle.boot.service.user.exception.CustomerNotFoundException;
import org.groodle.boot.service.user.exception.UserNotFoundException;
import org.groodle.boot.service.user.model.User;
import org.groodle.boot.service.user.repository.CustomerRepository;
import org.groodle.boot.service.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

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

    public UserDto create(UserDto request) {
        userRepository.findById(request.getCustomerId()).ifPresent(user -> {
            throw new CustomerAlreadyRegisteredException(user.getId());
        });
        customerRepository.findAll().forEach(customer -> log.info("{}", customer));
        customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));
        User saved = userRepository.save(User.builder().id(request.getCustomerId()).username(request.getId()).password(request.getPassword()).build());
        return UserDto.builder().id(saved.getUsername()).build();
    }

    public UserDto read(String username) {
        org.groodle.boot.service.user.model.User user = userRepository.findFirstByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return UserDto.builder().id(user.getUsername()).password(user.getPassword()).build();
    }

    public UserDto update(UserDto request, String userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        Objects.requireNonNull(request,"UserPartialUpdateRequest cannot be null");
        final User user = userRepository.findFirstByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Optional.ofNullable(request.getPassword()).ifPresent(user::setPassword);
        Optional.ofNullable(request.getId()).ifPresent(user::setUsername);
        User saved = userRepository.save(user);
        return UserDto.builder().id(saved.getUsername()).build();
    }

    public UserDto delete(String id) {
        Objects.requireNonNull(id, "User ID cannot be null");
        userRepository.deleteByUsername(id);
        return UserDto.builder().id(id).build();
    }
}
