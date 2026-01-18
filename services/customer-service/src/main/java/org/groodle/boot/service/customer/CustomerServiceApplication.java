package org.groodle.boot.service.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
