package org.groodle.boot.service.employee.repository;

import org.groodle.boot.service.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

//    public Mono<Employee> findEmployeeById(String id)
//    {
//        return Mono.just(employeeData.get(id));
//    }
//
//    public Flux<Employee> findAllEmployees() {
//        return Flux.fromIterable(employeeData.values());
//    }
}
