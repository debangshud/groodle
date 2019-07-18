package org.dasgupta.sample.springboot.jpa.h2.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.dasgupta.sample.springboot.jpa.h2.repository.employee",
        entityManagerFactoryRef = "employeeEntityManagerFactory",
        transactionManagerRef = "employeeTransactionManager"
)
public class EmployeeDatabaseConfiguration {

    private static final String EMPLOYEE_PREFIX = "employee.datasource";

    @Bean(name = "employeeDataSource")
    @ConfigurationProperties(prefix = EMPLOYEE_PREFIX)
    public DataSource employeeDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("employeeDataSource") DataSource dataSource,
            JpaProperties jpaProperties,
            HibernateProperties hibernateProperties) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(dataSource)
                .packages("org.dasgupta.sample.springboot.jpa.h2.entity.employee")
                .persistenceUnit("employee")
                .properties(properties)
                .build();
    }

    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("employeeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
