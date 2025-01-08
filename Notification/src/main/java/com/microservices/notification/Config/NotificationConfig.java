package com.microservices.notification.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.notification}")
    private String notificationQueueName;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    // Bean for TopicExchange
    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    // Bean for Queue with unique name
    @Bean
    public Queue notificationQueue() {
        return new Queue(this.notificationQueueName);
    }

    // Binding the queue to the exchange with a routing key
    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder
                .bind(this.notificationQueue()) // Use the notificationQueue() bean directly
                .to(this.internalTopicExchange()) // Use the internalTopicExchange() bean directly
                .with(this.internalNotificationRoutingKey); // Use the routing key
    }

    // Getter methods (Optional: if needed for other configurations or tests)
    public String getInternalExchange() {
        return this.internalExchange;
    }

    public String getNotificationQueueName() {
        return this.notificationQueueName;
    }

    public String getInternalNotificationRoutingKey() {
        return this.internalNotificationRoutingKey;
    }
}
