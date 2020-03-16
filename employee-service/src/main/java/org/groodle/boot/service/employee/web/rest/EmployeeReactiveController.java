package org.groodle.boot.service.employee.web.rest;

import org.groodle.boot.service.employee.model.Employee;
import org.groodle.boot.service.employee.repository.EmployeeRepository;
import org.groodle.boot.service.employee.web.errors.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
public class EmployeeReactiveController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/{id}")
    private Mono<Employee> getById(@PathVariable Long id) {

        Optional<Employee> optionalEmployee = repository.findById(id);
        if(optionalEmployee.isPresent()){
            return Mono.just(optionalEmployee.get());
        }else{
            throw new EmployeeNotFoundException();
        }
    }

    @GetMapping
    private Flux<Employee> getAll() {
        return Flux.fromIterable(repository.findAll());
    }
}
