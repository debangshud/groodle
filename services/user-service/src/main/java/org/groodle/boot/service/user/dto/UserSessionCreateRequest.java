package org.groodle.boot.service.user.dto;

import lombok.Data;

@Data
public class UserSessionCreateRequest {
    private String password;
}
