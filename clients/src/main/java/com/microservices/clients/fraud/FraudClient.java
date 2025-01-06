package com.microservices.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// The FeignClient annotation marks this interface as a Feign client
// that communicates with a remote fraud detection service.
@FeignClient(
        // The name of the fraud detection service. Feign will use this to identify the service.
        name = "fraud",
        // The URL of the fraud detection service, fetched from the application properties or environment variables.
        url = "${clients.fraud.url}"
)
public interface FraudClient {

    // This method will check whether a customer is a fraudster.
    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
