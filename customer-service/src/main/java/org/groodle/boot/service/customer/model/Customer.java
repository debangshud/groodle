package org.groodle.boot.service.customer.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
}
