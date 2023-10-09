package org.groodle.boot.service.employee.web.rest;

import org.groodle.boot.service.employee.model.Employee;
import org.groodle.boot.service.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    private Mono<Employee> getById(@PathVariable Long id) {
        return Mono.just(employeeService.getById(id));
    }

    @GetMapping
    private Flux<Employee> getAll() {
        return Flux.fromIterable(employeeService.getAll());
    }
}
