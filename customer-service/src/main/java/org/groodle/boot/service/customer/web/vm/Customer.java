package org.groodle.boot.service.customer.web.vm;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
}
