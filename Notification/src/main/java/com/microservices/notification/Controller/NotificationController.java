package com.microservices.notification.Controller;


import com.microservices.clients.notification.NotificationRequest;
import com.microservices.notification.Services.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody final NotificationRequest notificationRequest) {
        NotificationController.log.info("New notification... {}", notificationRequest);
        this.notificationService.send(notificationRequest);
    }
}