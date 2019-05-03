package org.dasgupta.sample.springboot.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SampleSpringBootDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringBootDockerApplication.class, args);
	}
}
