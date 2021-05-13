package org.groodle.boot.service.user.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.service.UserService;
import org.groodle.boot.service.user.web.vm.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserCreateResponse create(@RequestBody UserCreateRequest request) {
        log.info("{}", request);
        try {
            return userService.create(request);
        } finally {
            log.info("User created");
        }
    }

    @GetMapping("/{username}")
    public UserRetrieveResponse read(@PathVariable String username) {
        log.info("Username: {}", username);
        try {
            return userService.read(username);
        } finally {
            log.info("Returned");
        }
    }

    @PatchMapping("/{username}")
    public UserPartialUpdateResponse update(@RequestBody UserPartialUpdateRequest request, @PathVariable String username) {
        log.info("{}", request);
        try {
            return userService.update(request, username);
        } finally {
            log.info("User updated");
        }
    }
}
