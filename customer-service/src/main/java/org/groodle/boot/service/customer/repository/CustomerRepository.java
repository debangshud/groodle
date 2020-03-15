package org.groodle.boot.service.customer.repository;

import org.groodle.boot.service.customer.web.vm.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

}
