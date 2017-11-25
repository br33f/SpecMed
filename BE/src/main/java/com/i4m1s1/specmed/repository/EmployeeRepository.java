package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
