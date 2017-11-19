package com.i4m1s1.specmed.Repository;

import com.i4m1s1.specmed.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    public List<Employee> findByFirstName(String firstName);
}
