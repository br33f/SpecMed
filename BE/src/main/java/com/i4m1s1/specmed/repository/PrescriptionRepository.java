package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repozytorium zawierające dane recept
 */

public interface PrescriptionRepository extends MongoRepository<Prescription, String> {

}
