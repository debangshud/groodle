package org.acacia.sample.springboot.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("client")
                .secret("clientpassword")
                .scopes("read", "write")
                .authorizedGrantTypes("authorization_code"
                        , "password"
                        , "refresh_token"
                        , "implicit"
                        , "client_credential"
                )
                .accessTokenValiditySeconds(30)
                .refreshTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()");
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
