package org.groodle.boot.service.user.web.vm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateResponse {
    private String status;
}
