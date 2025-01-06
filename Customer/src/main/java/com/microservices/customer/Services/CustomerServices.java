package com.microservices.customer.Services;

import com.microservices.amqp.RabbitMQMessageProducer;
import com.microservices.clients.fraud.FraudCheckResponse;
import com.microservices.clients.fraud.FraudClient;
import com.microservices.clients.notification.NotificationRequest;
import com.microservices.customer.Dto.CustomerRegistrationRequest;
import com.microservices.customer.Entity.Customer;
import com.microservices.customer.Repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServices {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    public void registerCustomer(final CustomerRegistrationRequest request) {
        final Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        this.customerRepository.saveAndFlush(customer);

        final FraudCheckResponse fraudCheckResponse =
                this.fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        final NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Microservices...",
                        customer.getFirstName())
        );

        this.rabbitMQMessageProducer.publish(notificationRequest, "internal.exchange",
                "internal.notification.routing-key");

    }
}