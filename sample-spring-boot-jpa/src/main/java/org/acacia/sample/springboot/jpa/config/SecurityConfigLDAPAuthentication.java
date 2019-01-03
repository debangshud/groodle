package org.dasgupta.sample.springboot.jpa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class SecurityConfigLDAPAuthentication extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
//                .antMatchers("/employees")
                .anyRequest()
                .hasAuthority("API_USER")
//                .anyRequest()
//                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("rishan").password("rishan").roles("USER").authorities("API_USER");
        auth
                .inMemoryAuthentication()
                .withUser("rishan")
                .password("rishan")
                .authorities("API_USER")
                .and()
                .withUser("debangshu")
                .password("debangshu")
                .authorities("ADMIN");

    }
}
