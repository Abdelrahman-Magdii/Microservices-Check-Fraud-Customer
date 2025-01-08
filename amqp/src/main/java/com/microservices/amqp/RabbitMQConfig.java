package com.microservices.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class contains configuration settings for RabbitMQ in a Spring application.
 * It sets up the necessary beans for messaging, including message converters and listeners.
 */
@Configuration
@AllArgsConstructor
public class RabbitMQConfig {

    // ConnectionFactory to establish a connection to RabbitMQ.
    private final ConnectionFactory connectionFactory;

    /**
     * Configures and returns an AmqpTemplate that allows sending messages to RabbitMQ.
     * It uses RabbitTemplate, which is the standard implementation of AmqpTemplate.
     *
     * @return the configured AmqpTemplate
     */
    @Bean
    public AmqpTemplate amqpTemplate() {
        // Creating a new RabbitTemplate instance with the provided connection factory.
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);

        // Set the message converter for serializing and deserializing messages.
        rabbitTemplate.setMessageConverter(this.jacksonConverter());

        // Return the configured RabbitTemplate (AmqpTemplate).
        return rabbitTemplate;
    }

    /**
     * Configures and returns a SimpleRabbitListenerContainerFactory.
     * This factory is used to create message listeners for RabbitMQ queues.
     *
     * @return the configured SimpleRabbitListenerContainerFactory
     */
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        // Create a new factory instance.
        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        // Set the connection factory for the factory.
        factory.setConnectionFactory(this.connectionFactory);

        // Set the message converter for the factory to handle message serialization/deserialization.
        factory.setMessageConverter(this.jacksonConverter());

        // Return the configured factory.
        return factory;
    }

    /**
     * Creates and returns a Jackson2JsonMessageConverter.
     * This converter is responsible for converting messages to/from JSON format using Jackson.
     *
     * @return the Jackson2JsonMessageConverter for JSON message conversion
     */
    @Bean
    public MessageConverter jacksonConverter() {
        // Return a new instance of Jackson2JsonMessageConverter for JSON serialization.
        return new Jackson2JsonMessageConverter();
    }

}
