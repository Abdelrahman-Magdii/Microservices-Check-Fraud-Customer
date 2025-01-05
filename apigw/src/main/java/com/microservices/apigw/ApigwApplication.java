package com.microservices.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApigwApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApigwApplication.class, args);
    }

}
