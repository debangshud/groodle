package org.groodle.boot.service.user.web.rest;

import org.groodle.boot.service.user.service.UserSessionService;
import org.groodle.boot.service.user.web.vm.UserSessionCreateRequest;
import org.groodle.boot.service.user.web.vm.UserSessionCreateResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserSessionController {

    private final UserSessionService userSessionService;

    public UserSessionController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @PostMapping("/{username}/sessions")
    public UserSessionCreateResponse create (@RequestBody UserSessionCreateRequest request, @PathVariable String username) {
        return userSessionService.create(request,username);
    }
}
