package org.groodle.boot.service.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.dto.CustomerDto;
import org.groodle.boot.service.customer.exception.CustomerNotFoundException;
import org.groodle.boot.service.customer.model.Customer;
import org.groodle.boot.service.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {
    @Value("${test.prop:test}")
    private String testProp;
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerDto> findAll() {
        log.info("Test Property: {}", testProp);
        return repository.findAll().stream().map(customer -> CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build()).collect(Collectors.toList());
    }

    public CustomerDto findByID(Long id) {

        return repository.findById(id).map(customer -> CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build()).orElseThrow(CustomerNotFoundException::new);
    }

    public CustomerDto create(CustomerDto customer) {
        Customer saved = repository.save(Customer.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build());
        return CustomerDto.builder().id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .build();
    }
}
