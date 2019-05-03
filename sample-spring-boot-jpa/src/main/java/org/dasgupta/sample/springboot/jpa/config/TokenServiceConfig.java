package org.dasgupta.sample.springboot.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
public class TokenServiceConfig {

//    @Bean
//    public RemoteTokenServices tokenService() {
//        RemoteTokenServices tokenService = new RemoteTokenServices();
//        tokenService.setCheckTokenEndpointUrl(
//                "http://localhost:9090/oauth/check_token");
//        tokenService.setClientId("client");
//        tokenService.setClientSecret("clientpassword");
//        return tokenService;
//    }
}
