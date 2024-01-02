package com.esg.readerapi.repository;

import com.esg.readerapi.model.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {

    @Query("SELECT * FROM customer WHERE customer_ref = :refNumber LIMIT 1")
    Mono<Customer> findCustomerByReferenceNumber(Integer refNumber);
}
