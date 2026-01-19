package org.groodle.boot.service.employee.controller;

import org.groodle.boot.service.employee.dto.EmployeeDto;
import org.groodle.boot.service.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeReactiveController {
    private final EmployeeService employeeService;

    public EmployeeReactiveController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    private Mono<EmployeeDto> findByID(@PathVariable Long id) {
        return Mono.just(employeeService.findByID(id));
    }

    @GetMapping
    private Flux<EmployeeDto> getAll() {
        return Flux.fromIterable(employeeService.findAll());
    }
}
