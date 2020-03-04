package org.groodle.boot.service.customer.web.vm;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
}
