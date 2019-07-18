package org.dasgupta.sample.springboot.jpa.h2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.sql.DataSource;
import javax.validation.constraints.NotBlank;

public class DataSourceFactory {

    @Autowired
    private ConfigurableEnvironment environment;

    @NotBlank
    private String jdbcUrl;
    @NotBlank
    private String driverClassName;
    @NotBlank
    private String username;
    private String password;

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DataSource build(){

        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .build();
    }

}
