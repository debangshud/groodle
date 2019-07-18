package org.dasgupta.sample.springboot.jpa.h2;

import com.zaxxer.hikari.HikariDataSource;
import org.dasgupta.sample.springboot.jpa.h2.entity.customer.Customer;
import org.dasgupta.sample.springboot.jpa.h2.entity.employee.Employee;
import org.dasgupta.sample.springboot.jpa.h2.repository.customer.CustomerRepository;
import org.dasgupta.sample.springboot.jpa.h2.repository.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class Application {

    private static final Logger LOG = getLogger(Application.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            @Qualifier("customerDataSource") HikariDataSource customerDataSource,
            @Qualifier("employeeDataSource") HikariDataSource employeeDataSource
    ) {
        return args -> {
            LOG.info("<Start>Command Line Runner................");
            LOG.info("Customer......");
            LOG.info("DataSourceProperties: {}",customerDataSource.getDataSourceProperties());
            LOG.info("MaximumPoolSize: {}",customerDataSource.getMaximumPoolSize());
            LOG.info("MinimumIdle: {}",customerDataSource.getMinimumIdle());
            LOG.info("Schema: {}",customerDataSource.getSchema());
            LOG.info("Employee......");
            LOG.info("DataSourceProperties: {}",employeeDataSource.getDataSourceProperties());
            LOG.info("MaximumPoolSize: {}",employeeDataSource.getMaximumPoolSize());
            LOG.info("MinimumIdle: {}",employeeDataSource.getMinimumIdle());
            LOG.info("Schema: {}",employeeDataSource.getSchema());

            customerRepository.save(Customer.builder().id(1).firstName("Debangshu").lastName("Dasgupta").build());
            customerRepository.save(Customer.builder().id(2).firstName("Rishan").lastName("Dasgupta").build());
            customerRepository.save(Customer.builder().id(3).firstName("Manjistha").lastName("Dasgupta").build());

            employeeRepository.save(Employee.builder().id(1).firstName("Ajoy").lastName("Dasgupta").build());
            employeeRepository.save(Employee.builder().id(2).firstName("Gita").lastName("Dasgupta").build());
        };
    }
}
