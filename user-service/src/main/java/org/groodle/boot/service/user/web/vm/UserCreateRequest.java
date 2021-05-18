package org.groodle.boot.service.user.web.vm;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCreateRequest {
    private Long customerId;
    private String userId;
    private String password;
}
