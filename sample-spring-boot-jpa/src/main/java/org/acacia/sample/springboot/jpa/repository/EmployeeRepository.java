package org.acacia.sample.springboot.jpa.repository;

import org.acacia.sample.springboot.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
