package org.dasgupta.sample.springboot.jpa.h2.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.dasgupta.sample.springboot.jpa.h2.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class CustomerEndpoint {


    @Autowired
    private DataSource customerDataSource;

    @GetMapping("/customers")
    public ResponseEntity<List> getAllCustomers() throws SQLException {

        log.info("DataSource: {}",customerDataSource.getConnection().getClientInfo());
        Customer customer = new Customer(1,"Debangshu","Dasgupta");


        return new ResponseEntity<>(Arrays.asList(customer), HttpStatus.OK);
    }

}
