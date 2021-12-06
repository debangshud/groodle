package org.groodle.boot.service.user.event;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDeletionEvent {

    private String userId;
}
