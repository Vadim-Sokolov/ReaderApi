package com.esg.readerapi.service;

import com.esg.readerapi.converter.CustomerConverter;
import com.esg.readerapi.model.Customer;
import com.esg.readerapi.model.dto.CustomerDto;
import com.esg.readerapi.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private final CustomerRepository customerRepository = mock(CustomerRepository.class);

    private final CustomerService customerService = new CustomerService(customerRepository);

    @Test
    public void testSaveCustomer() {
        // GIVEN
        var customerDto = new CustomerDto();
        var customerToSave = CustomerConverter.convertDtoToCustomer(customerDto);
        var expected = new Customer();

        when(customerRepository.save(customerToSave)).thenReturn(Mono.just(expected));

        // WHEN
        var actual = customerService.saveCustomer(customerDto);

        // THEN
        StepVerifier.create(actual)
                .expectNextMatches(savedDto -> savedDto.equals(CustomerConverter.convertCustomerToDto(expected)))
                .verifyComplete();

        verify(customerRepository, times(1)).save(customerToSave);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testGetCustomerByReferenceNumber() {
        // GIVEN
        var referenceNumber = 123;
        var expected = new Customer();
        var expectedCustomerDto = CustomerConverter.convertCustomerToDto(expected);

        when(customerRepository.findCustomerByReferenceNumber(referenceNumber)).thenReturn(Mono.just(expected));

        // WHEN
        var actual = customerService.getCustomerByReferenceNumber(referenceNumber);

        // THEN
        StepVerifier.create(actual)
                .expectNextMatches(customerDto -> customerDto.equals(expectedCustomerDto))
                .verifyComplete();

        verify(customerRepository, times(1)).findCustomerByReferenceNumber(referenceNumber);
        verifyNoMoreInteractions(customerRepository);
    }
}