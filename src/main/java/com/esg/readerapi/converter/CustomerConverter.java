package com.esg.readerapi.converter;

import com.esg.readerapi.model.Customer;
import com.esg.readerapi.model.dto.CustomerDto;

public class CustomerConverter {

    public static CustomerDto convertCustomerToDto(Customer customer) {
        return CustomerDto.builder()
                .customerRef(customer.getCustomerRef())
                .customerName(customer.getCustomerName())
                .addressLine1(customer.getAddressLine1())
                .addressLine2(customer.getAddressLine2())
                .town(customer.getTown())
                .county(customer.getCounty())
                .country(customer.getCountry())
                .postcode(customer.getPostcode())
                .build();
    }

    public static Customer convertDtoToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .customerRef(customerDto.getCustomerRef())
                .customerName(customerDto.getCustomerName())
                .addressLine1(customerDto.getAddressLine1())
                .addressLine2(customerDto.getAddressLine2())
                .town(customerDto.getTown())
                .county(customerDto.getCounty())
                .country(customerDto.getCountry())
                .postcode(customerDto.getPostcode())
                .build();
    }
}
