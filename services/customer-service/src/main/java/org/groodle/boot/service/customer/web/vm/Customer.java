package org.groodle.boot.service.customer.web.vm;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
}
