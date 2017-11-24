package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EmployeeRepository extends MongoRepository<Employee, String> {
    public List<Employee> findByFirstName(String firstName);
}
