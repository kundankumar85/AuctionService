package com.cars.auction.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@EnableJpaRepositories(basePackages="com.cars.auction.repository" )
public class AuctionConfig {

    @Bean
    public Module hibernateModule(){
        return new Hibernate5Module();
    }
}
