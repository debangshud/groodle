package org.groodle.boot.service.user.service;

import org.groodle.boot.service.user.exception.IncorrectPasswordException;
import org.groodle.boot.service.user.exception.UserNotFoundException;
import org.groodle.boot.service.user.model.User;
import org.groodle.boot.service.user.model.UserSession;
import org.groodle.boot.service.user.repository.UserRepository;
import org.groodle.boot.service.user.repository.UserSessionRepository;
import org.groodle.boot.service.user.web.vm.UserSessionCreateRequest;
import org.groodle.boot.service.user.web.vm.UserSessionCreateResponse;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {
    private final UserSessionRepository userSessionRepository;
    private final UserRepository userRepository;

    public UserSessionService(UserSessionRepository userSessionRepository, UserRepository userRepository) {
        this.userSessionRepository = userSessionRepository;
        this.userRepository = userRepository;
    }

    public UserSessionCreateResponse create(UserSessionCreateRequest request, String username) {
        User user = userRepository.findFirstByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        if (user.getPassword().equals(request.getPassword())) {
            UserSession session = userSessionRepository.save(UserSession.builder().username(username).build());
            return UserSessionCreateResponse.builder().createdTime(session.getTimestamp()).status("Success").build();
        } else {
            throw new IncorrectPasswordException();
        }
    }
}
