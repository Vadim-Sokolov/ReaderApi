package com.esg.readerapi.controller;

import com.esg.readerapi.model.dto.CustomerDto;
import com.esg.readerapi.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    private final CustomerService customerService = mock(CustomerService.class);

    private final CustomerController customerController = new CustomerController(customerService);

    @Test
    public void testSaveCustomer() {
        // GIVEN
        var customerToSave = new CustomerDto();
        var expected = new CustomerDto();

        when(customerService.saveCustomer(customerToSave)).thenReturn(Mono.just(expected));

        // WHEN
        var actual = customerController.saveCustomer(customerToSave);

        // THEN
        StepVerifier.create(actual)
                .expectNextMatches(responseEntity ->
                        responseEntity.getStatusCode() == HttpStatus.CREATED &&
                                Objects.equals(responseEntity.getBody(), expected))
                .verifyComplete();

        verify(customerService, times(1)).saveCustomer(customerToSave);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testGetCustomerByReferenceNumber() {
        // GIVEN
        var refNumber = 123;
        var expected = new CustomerDto();

        when(customerService.getCustomerByReferenceNumber(refNumber)).thenReturn(Mono.just(expected));

        // WHEN
        var actual = customerController.getCustomerByReferenceNumber(refNumber);

        // THEN
        StepVerifier.create(actual)
                .expectNextMatches(responseEntity ->
                        responseEntity.getStatusCode() == HttpStatus.OK &&
                                Objects.equals(responseEntity.getBody(), expected))
                .verifyComplete();

        verify(customerService, times(1)).getCustomerByReferenceNumber(refNumber);
        verifyNoMoreInteractions(customerService);
    }
}