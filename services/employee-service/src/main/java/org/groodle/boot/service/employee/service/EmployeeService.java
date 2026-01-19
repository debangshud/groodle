package org.groodle.boot.service.employee.service;

import org.groodle.boot.service.employee.dto.EmployeeDto;
import org.groodle.boot.service.employee.exception.EmployeeNotFoundException;
import org.groodle.boot.service.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto findByID(Long id) {
        return employeeRepository.findById(id).map(employee -> EmployeeDto.builder().id(employee.getId()).firstName(employee.getFirstName()).lastName(employee.getLastName()).build()).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(employee -> EmployeeDto.builder().id(employee.getId()).firstName(employee.getFirstName()).lastName(employee.getLastName()).build()).toList();
    }
}
