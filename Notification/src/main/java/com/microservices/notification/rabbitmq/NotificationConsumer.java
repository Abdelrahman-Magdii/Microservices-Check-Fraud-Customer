package com.microservices.notification.rabbitmq;

import com.microservices.clients.notification.NotificationRequest;
import com.microservices.notification.Services.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(final NotificationRequest notificationRequest) {
        NotificationConsumer.log.info("Consumed {} from queue", notificationRequest);
        this.notificationService.send(notificationRequest);
    }
}
