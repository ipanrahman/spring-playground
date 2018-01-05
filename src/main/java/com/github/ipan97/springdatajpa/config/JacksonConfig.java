package com.github.ipan97.springdatajpa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ipan Taupik Rahman.
 * Date: 05/01/18
 * Time: 16:30
 * Project spring-data-jpa
 * Package com.github.ipan97.springdatajpa.config
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
