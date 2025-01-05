package com.microservices.customer.Controller;

import com.microservices.customer.Dto.CustomerRegistrationRequest;
import com.microservices.customer.Services.CustomerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {


    private final CustomerServices customerService;

    @Autowired
    public CustomerController(final CustomerServices customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public void registerCustomer(@RequestBody final CustomerRegistrationRequest customerRegistrationRequest) {
        CustomerController.log.info("new customer registration {}", customerRegistrationRequest);
        this.customerService.registerCustomer(customerRegistrationRequest);
    }


}
