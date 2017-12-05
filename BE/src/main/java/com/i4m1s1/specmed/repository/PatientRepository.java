package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PatientRepository extends MongoRepository<Patient, String> {

}
