package org.groodle.boot.service.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.dto.CustomerDto;
import org.groodle.boot.service.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findByID(@PathVariable("id") Long id) {
        return ok(service.findByID(id));

    }

    @PostMapping()
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customer) {
        CustomerDto result = service.create(customer);
        URI uri = URI.create("/api/v1/customers/" + result.getId());
        return created(uri).body(result);
    }
}
