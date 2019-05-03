package org.dasgupta.sample.springboot.jpa.repository;

import org.dasgupta.sample.springboot.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
