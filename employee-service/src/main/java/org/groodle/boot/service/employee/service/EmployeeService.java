package org.groodle.boot.service.employee.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.employee.model.Employee;
import org.groodle.boot.service.employee.repository.EmployeeRepository;
import org.groodle.boot.service.employee.web.errors.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getById(Long id) {
        log.info("getById({}) called", id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public List<Employee> getAll() {
        log.info("getAll() called");
        return employeeRepository.findAll();
    }
}
