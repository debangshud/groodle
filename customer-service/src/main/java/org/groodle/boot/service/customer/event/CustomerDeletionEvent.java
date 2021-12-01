package org.groodle.boot.service.customer.event;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDeletionEvent implements Serializable {
    private Long id;
}
