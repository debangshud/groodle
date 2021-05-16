package org.groodle.boot.service.user.web.vm;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorMessage {
    private LocalDateTime timestamp;
    private String message;
}
