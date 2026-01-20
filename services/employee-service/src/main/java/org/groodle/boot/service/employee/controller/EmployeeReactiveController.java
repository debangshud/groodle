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
    private final EmployeeService service;

    public EmployeeReactiveController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    private Mono<EmployeeDto> findByID(@PathVariable Long id) {
        return Mono.just(service.findByID(id));
    }

    @GetMapping
    private Flux<EmployeeDto> getAll() {
        return Flux.fromIterable(service.findAll());
    }
}
