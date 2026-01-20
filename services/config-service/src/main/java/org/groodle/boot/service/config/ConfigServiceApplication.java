package org.groodle.boot.service.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {
	static void main(String[] args) {
		SpringApplication.run(ConfigServiceApplication.class, args);
	}
}
