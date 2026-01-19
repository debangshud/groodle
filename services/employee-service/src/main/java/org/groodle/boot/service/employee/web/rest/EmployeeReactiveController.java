package org.groodle.boot.service.employee.web.rest;

import org.groodle.boot.service.employee.model.Employee;
import org.groodle.boot.service.employee.repository.EmployeeRepository;
import org.groodle.boot.service.employee.web.errors.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeReactiveController {

    private final EmployeeRepository employeeRepository;

    public EmployeeReactiveController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{id}")
    private Mono<Employee> getById(@PathVariable Long id) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return Mono.just(optionalEmployee.get());
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @GetMapping
    private Flux<Employee> getAll() {
        return Flux.fromIterable(employeeRepository.findAll());
    }
}
