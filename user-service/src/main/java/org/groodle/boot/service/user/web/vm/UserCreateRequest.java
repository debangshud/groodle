package org.groodle.boot.service.user.web.vm;

import lombok.Data;
import lombok.ToString;
import sun.security.util.Password;

@Data
@ToString
public class UserCreateRequest {
    private Long customerId;
    private String username;
    private String password;
}
