package org.groodle.boot.service.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.service.UserService;
import org.groodle.boot.service.user.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/api/v1/users")
@RestController
@Slf4j
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        log.info("{}", user);
        UserDto result = service.create(user);
        URI uri = URI.create("/api/v1/users/" + result.getId());
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findByID(@PathVariable String id) {
        log.info("Id: {}", id);
        return ok(service.read(id));
    }
    @GetMapping()
    public ResponseEntity<List<UserDto>> findAll() {
        return ok(service.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user, @PathVariable String id) {
        log.info("{}", user);
        return ok(service.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable String id) {
        return ok(service.delete(id));
    }
}
