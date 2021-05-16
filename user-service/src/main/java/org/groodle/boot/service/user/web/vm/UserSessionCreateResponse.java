package org.groodle.boot.service.user.web.vm;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserSessionCreateResponse {
    private String status;
    private LocalDateTime createdTime;
}
