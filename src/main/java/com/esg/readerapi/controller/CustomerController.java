package com.esg.readerapi.controller;

import com.esg.readerapi.model.dto.CustomerDto;
import com.esg.readerapi.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerDto>> saveCustomer(@RequestBody CustomerDto customerDto) {
        log.debug("Calling customerService.saveCustomer() method.");
        return customerService.saveCustomer(customerDto)
                .map(savedCustomer -> new ResponseEntity<>(savedCustomer, HttpStatus.CREATED));
    }

    @GetMapping("/{refNumber}")
    public String getCustomerByReferenceNumber(@PathVariable Integer refNumber) {
        return "Requested ID: " + refNumber;
    }
}
