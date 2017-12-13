package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repozytorium zawierające dane pacjentów
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
    /**
     * metoda zapewniająca wyszukanie danych klienta o konkretnym id
     * @param id id klienta
     * @return dane klienta o danym id
     */
    Customer findById(String id);
}
