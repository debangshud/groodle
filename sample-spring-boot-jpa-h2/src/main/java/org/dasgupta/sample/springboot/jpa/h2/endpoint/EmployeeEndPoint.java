package org.dasgupta.sample.springboot.jpa.h2.endpoint;

import org.dasgupta.sample.springboot.jpa.h2.repository.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class EmployeeEndPoint {

    private static final Logger LOG = getLogger(EmployeeEndPoint.class);

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public ResponseEntity<Iterable> getAllEmployees() {

        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
}
