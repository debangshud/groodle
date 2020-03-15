package org.groodle.boot.service.customer.web.vm;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
}
