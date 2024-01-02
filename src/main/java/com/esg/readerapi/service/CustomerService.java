package com.esg.readerapi.service;

import com.esg.readerapi.converter.CustomerConverter;
import com.esg.readerapi.model.dto.CustomerDto;
import com.esg.readerapi.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<CustomerDto> saveCustomer(CustomerDto customerDto) {
        log.debug("Converting Dto to customer entity");
        var customerToSave = CustomerConverter.convertDtoToCustomer(customerDto);
        log.debug("Saving customer repository");
        return customerRepository.save(customerToSave)
                .map(savedCustomer -> {
                    log.debug("Customer saved successfully. Converting result to Dto.");
                    return CustomerConverter.convertCustomerToDto(savedCustomer);
                })
                .doOnError(throwable -> log.error("Error saving customer", throwable))
                .doOnSuccess(dto -> log.debug("Converted result to Dto. Returning result."));
    }

    public Mono<CustomerDto> getCustomerByReferenceNumber(Integer referenceNumber) {
        log.debug("Retrieving customer from repository");
        return customerRepository.findCustomerByReferenceNumber(referenceNumber)
                .map(customer -> {
                    log.debug("Customer found. Converting result to Dto.");
                    return CustomerConverter.convertCustomerToDto(customer);
                })
                .doOnError(throwable -> log.error("Error getting customer by reference number", throwable))
                .doOnSuccess(customerDto -> log.debug("Converted result to Dto. Returning result."));
    }
}
