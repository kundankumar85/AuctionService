package com.cars.auction.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages="com.cars.auction.repository" )
public class AuctionConfig {

    @Bean
    public Module hibernateModule(){
        return new Hibernate5Module();
    }
}
