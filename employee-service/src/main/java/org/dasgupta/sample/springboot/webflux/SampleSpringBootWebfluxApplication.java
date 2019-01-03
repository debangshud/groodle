package org.dasgupta.sample.springboot.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SampleSpringBootWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleSpringBootWebfluxApplication.class, args);
    }

}

