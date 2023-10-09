package org.groodle.boot.service.customer.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.model.Customer;
import org.groodle.boot.service.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAll() {
        log.info("method:getAll()");
        return new ResponseEntity<>(customerService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        log.info("method: getById(id)");
        log.debug("Requested Customer Id:{}", id);
        return new ResponseEntity<>(customerService.getById(id),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        log.info("method:create(customer)");
        log.debug("Customer:{}", customer);
        return new ResponseEntity<>(customerService.create(customer),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        log.info("deleteById({})",id);
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
