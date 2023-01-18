package com.nchain;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan({"com.nchain.controller", "com.nchain.service"})
@EntityScan("com.nchain.entity")
@EnableJpaRepositories("com.nchain.repository")
@Configuration
public class SpringBootApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringBootApplication.class, args);
        System.out.println("NchainApp running");
    }

}