package org.groodle.boot.service.customer.web.vm;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
}
