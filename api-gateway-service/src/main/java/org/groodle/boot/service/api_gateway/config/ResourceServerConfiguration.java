package org.groodle.boot.service.api_gateway.config;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private JwtProperties jwtProperties;

    public ResourceServerConfiguration(final JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPublicKeyAsString());
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
        return tokenStore;
    }

    @Bean
    public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll().anyRequest().authenticated();

    }

    private String getPublicKeyAsString() {
        try {
            return IOUtils.toString(jwtProperties.getPublicKey().getInputStream(), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
