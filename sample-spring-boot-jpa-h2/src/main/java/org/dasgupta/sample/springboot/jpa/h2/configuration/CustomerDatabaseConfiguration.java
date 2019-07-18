package org.dasgupta.sample.springboot.jpa.h2.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = "org.dasgupta.sample.springboot.jpa.h2.repository.customer",
        entityManagerFactoryRef = "customerEntityManagerFactory",
        transactionManagerRef = "customerTransactionManager"
)
public class CustomerDatabaseConfiguration {

    private static final String CUSTOMER_PREFIX = "customer.datasource";

    @Primary
    @Bean(name = "customerDataSource")
    @ConfigurationProperties(prefix = CUSTOMER_PREFIX)
    public DataSource customerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("customerDataSource") DataSource dataSource,
            JpaProperties jpaProperties,
            HibernateProperties hibernateProperties) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(dataSource)
                .packages("org.dasgupta.sample.springboot.jpa.h2.entity.customer")
                .persistenceUnit("customer")
                .properties(properties)
                .build();
    }

    @Bean(name = "customerTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("customerEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
