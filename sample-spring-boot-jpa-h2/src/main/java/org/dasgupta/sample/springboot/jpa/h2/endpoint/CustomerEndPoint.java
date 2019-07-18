package org.dasgupta.sample.springboot.jpa.h2.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.dasgupta.sample.springboot.jpa.h2.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CustomerEndPoint {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public ResponseEntity<Iterable> getAllCustomers() {

        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

}
