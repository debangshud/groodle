package org.groodle.boot.service.user.config;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class OpenApiValidationConfig implements WebMvcConfigurer {
    private final OpenApiValidationInterceptor validationInterceptor;

    public OpenApiValidationConfig() {
        this.validationInterceptor = new OpenApiValidationInterceptor(OpenApiInteractionValidator.createFor("/oa3/openapi.json").build());
    }

    @Bean
    public Filter validationFilter(){
        return new OpenApiValidationFilter(
                true, // enable request validation
                true  // enable response validation
        );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validationInterceptor);
    }
}
