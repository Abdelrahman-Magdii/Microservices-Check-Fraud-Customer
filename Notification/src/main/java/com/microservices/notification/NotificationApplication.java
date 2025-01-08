package com.microservices.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.microservices.notification",
                "com.microservices.amqp",
        }
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {

    public static void main(final String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

}
