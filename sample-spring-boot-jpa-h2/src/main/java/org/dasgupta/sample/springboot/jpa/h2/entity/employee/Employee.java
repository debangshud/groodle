package org.dasgupta.sample.springboot.jpa.h2.entity.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String designation;
}
