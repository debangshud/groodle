package org.groodle.boot.service.user.web.vm;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPartialUpdateResponse {
    private String userId;
}
