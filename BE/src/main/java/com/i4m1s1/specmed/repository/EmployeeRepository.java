package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repozytorium zawierające dane o pracownikach
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee findById(String Id);
}
