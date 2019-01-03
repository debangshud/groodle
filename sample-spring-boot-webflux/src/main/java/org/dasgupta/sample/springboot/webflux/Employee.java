package org.dasgupta.sample.springboot.webflux;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
}
