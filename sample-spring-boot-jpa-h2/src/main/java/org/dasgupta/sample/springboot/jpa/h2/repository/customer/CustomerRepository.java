package org.dasgupta.sample.springboot.jpa.h2.repository.customer;

import org.dasgupta.sample.springboot.jpa.h2.entity.customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

}
