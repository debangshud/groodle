package org.dasgupta.sample.springboot.jpa.h2.repository.employee;

import org.dasgupta.sample.springboot.jpa.h2.entity.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
}
