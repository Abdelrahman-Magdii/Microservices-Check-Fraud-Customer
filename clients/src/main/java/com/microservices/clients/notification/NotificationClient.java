package com.microservices.clients.notification;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

// The FeignClient annotation indicates this interface is a Feign client
// used to call a remote service. It enables declarative REST API consumption in Spring.
@FeignClient(
        // The name of the service, which Feign uses to identify it.
        name = "notification",
        // The URL of the notification service, fetched from application properties or environment variables.
        url = "${clients.notification.url}"
)
public interface NotificationClient {

    // This method will send a notification request to the notification service.
    @PostMapping("api/v1/notification")
    // The endpoint to send the notification.
    void sendNotification(NotificationRequest notificationRequest);
    // The 'NotificationRequest' object will contain the details for the notification being sent.
}
