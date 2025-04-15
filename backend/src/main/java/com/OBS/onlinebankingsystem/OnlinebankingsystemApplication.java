package com.OBS.onlinebankingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.OBS.onlinebankingsystem.model")
//@EnableJpaRepositories("com.OBS.onlinebankingsystem.dao")
public class OnlinebankingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinebankingsystemApplication.class, args);
    }
}
