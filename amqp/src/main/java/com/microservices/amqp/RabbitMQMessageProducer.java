package com.microservices.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for publishing messages to a specified exchange in RabbitMQ.
 * It uses the AmqpTemplate to send the message with a routing key.
 */
@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    // The AmqpTemplate used for sending messages to RabbitMQ.
    private final AmqpTemplate amqpTemplate;

    /**
     * Publishes a message (payload) to a specified RabbitMQ exchange with a routing key.
     * It uses the AmqpTemplate to convert and send the message.
     *
     * @param payload    The message to be sent.
     * @param exchange   The exchange to which the message is published.
     * @param routingKey The routing key used to route the message within the exchange.
     */
    public void publish(final Object payload, final String exchange, final String routingKey) {
        // Log information about the message that will be published.
        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);

        // Send the message to the specified exchange with the routing key and payload.
        this.amqpTemplate.convertAndSend(exchange, routingKey, payload);

        // Log information after the message is successfully published.
        log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
    }
}
