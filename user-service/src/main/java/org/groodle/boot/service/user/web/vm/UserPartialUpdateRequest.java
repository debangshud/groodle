package org.groodle.boot.service.user.web.vm;

import lombok.Data;

@Data
public class UserPartialUpdateRequest {
    private String currentUsername;
    private String newUsername;
    private String currentPassword;
    private String newPassword;
}
