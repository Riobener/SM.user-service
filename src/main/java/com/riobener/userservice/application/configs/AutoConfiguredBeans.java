package com.riobener.userservice.application.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguredBeans {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
