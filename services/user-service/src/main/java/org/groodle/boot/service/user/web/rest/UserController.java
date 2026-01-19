package org.groodle.boot.service.user.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.service.UserService;
import org.groodle.boot.service.user.web.vm.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserCreateResponse> create(@RequestBody UserCreateRequest request) {
        log.info("{}", request);
        UserCreateResponse userCreateResponse = userService.create(request);
        return new ResponseEntity<>(userCreateResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRetrieveResponse> read(@PathVariable String userId) {
        log.info("Id: {}", userId);
        try {
            return new ResponseEntity<>(userService.read(userId), HttpStatus.OK);
        } finally {
            log.info("Returned");
        }
    }

    @PatchMapping("/{userId}")
    public UserPartialUpdateResponse update(@RequestBody UserPartialUpdateRequest request, @PathVariable String userId) {
        log.info("{}", request);
        try {
            return userService.update(request, userId);
        } finally {
            log.info("User updated");
        }
    }

    @DeleteMapping("/{userId}")
    public UserDeleteResponse delete(@PathVariable String userId) {
        return userService.delete(userId);
    }
}
