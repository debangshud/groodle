package org.dasgupta.sample.springboot.service.customer.repository;

import org.dasgupta.sample.springboot.service.customer.web.vm.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {

    private Map<String, Customer> customerData;

    public CustomerRepository() {
        customerData = new HashMap<>();
        customerData.put("1", Customer.builder().id("1").firstName("Debangshu").lastName("Dasgupta").build());
    }


    public Customer findCustomerById(String id) {
        return customerData.get(id);
    }

    public Customer createCustomer(Customer customer) {
        return Customer.builder().id("2").firstName("Rishan").lastName("Dasgupta").build();
    }
}
