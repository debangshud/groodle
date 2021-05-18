package org.groodle.boot.service.user.web.vm;

import lombok.Data;

@Data
public class UserPartialUpdateRequest {
    private String newUserId;
    private String currentPassword;
    private String newPassword;
}
