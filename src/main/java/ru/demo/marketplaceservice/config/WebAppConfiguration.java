package ru.demo.marketplaceservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Configuration
@EntityScan(basePackages = "ru.demo.marketplaceservice.entity")
@ComponentScan(basePackages = "ru.demo.marketplaceservice")
@EnableJpaRepositories(basePackages = "ru.demo.marketplaceservice.repository")
public class WebAppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
