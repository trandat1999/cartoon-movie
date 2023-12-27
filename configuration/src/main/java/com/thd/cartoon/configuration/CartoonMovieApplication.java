package com.thd.cartoon.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@AutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.thd.cartoon","com.thd.cartoon.entity","com.thd.cartoon.repository"})
@EntityScan(basePackages ="com.thd.cartoon.entity")
@EnableJpaRepositories(basePackages ="com.thd.cartoon.repository")
public class CartoonMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartoonMovieApplication.class, args);
    }

}
