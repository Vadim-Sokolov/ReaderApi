package com.esg.readerapi.converter;

import com.esg.readerapi.model.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import com.esg.readerapi.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerConverterTest {

    @Test
    void convertCustomerToDtoTest() {
        // GIVEN
        var customer = Customer.builder()
                .id(71L)
                .customerRef(34)
                .customerName("Joe Bloggs")
                .addressLine1("11 London Road")
                .addressLine2("")
                .town("Beecroft")
                .county("Kent")
                .country("UK")
                .postcode("RW1 7KJ")
                .build();

        var expected = CustomerDto.builder()
                .customerRef(34)
                .customerName("Joe Bloggs")
                .addressLine1("11 London Road")
                .addressLine2("")
                .town("Beecroft")
                .county("Kent")
                .country("UK")
                .postcode("RW1 7KJ")
                .build();

        // WHEN
        var actual = CustomerConverter.convertCustomerToDto(customer);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void convertDtoToCustomerTest() {
        // GIVEN
        var expected = Customer.builder()
                .customerRef(34)
                .customerName("Joe Bloggs")
                .addressLine1("11 London Road")
                .addressLine2("")
                .town("Beecroft")
                .county("Kent")
                .country("UK")
                .postcode("RW1 7KJ")
                .build();

        var customerDto = CustomerDto.builder()
                .customerRef(34)
                .customerName("Joe Bloggs")
                .addressLine1("11 London Road")
                .addressLine2("")
                .town("Beecroft")
                .county("Kent")
                .country("UK")
                .postcode("RW1 7KJ")
                .build();

        // WHEN
        var actual = CustomerConverter.convertDtoToCustomer(customerDto);

        // THEN
        assertEquals(expected, actual);
    }
}