package org.groodle.boot.service.customer.web.vm;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
@JsonPropertyOrder({"id", "firstName", "lastName"})
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
}
