package org.groodle.boot.service.user.web.vm;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRetrieveResponse {
    private String userId;
    private String password;
}
