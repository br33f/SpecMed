package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
