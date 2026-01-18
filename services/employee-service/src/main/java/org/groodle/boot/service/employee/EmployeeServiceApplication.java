package org.groodle.boot.service.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

}

