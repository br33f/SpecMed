package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repozytorium zawierające dane pacjentów
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
