package com.i4m1s1.specmed.Service;

import com.i4m1s1.specmed.Model.Employee;
import com.i4m1s1.specmed.Repository.EmployeeRepository;
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
