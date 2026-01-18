package org.groodle.boot.service.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAdminServer
@EnableDiscoveryClient
public class AdminServiceApplication {

	static void main(String[] args) {
		SpringApplication.run(AdminServiceApplication.class, args);
	}
}
