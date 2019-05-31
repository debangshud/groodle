package org.dasgupta.sample.springboot.jpa.h2.entity.employee;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Employee {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String designation;
}
