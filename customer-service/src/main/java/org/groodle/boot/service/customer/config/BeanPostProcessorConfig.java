package org.groodle.boot.service.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPostProcessorConfig {
    @Bean
    public RedisCacheConfigBeanPostProcessor redisCacheConfigBeanPostProcessor(){
        return new RedisCacheConfigBeanPostProcessor();
    }
}
