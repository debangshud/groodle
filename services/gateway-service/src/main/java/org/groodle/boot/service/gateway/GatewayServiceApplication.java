package org.groodle.boot.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

}
