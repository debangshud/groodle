package org.dasgupta.sample.springboot.jpa.h2.entity.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;

}
