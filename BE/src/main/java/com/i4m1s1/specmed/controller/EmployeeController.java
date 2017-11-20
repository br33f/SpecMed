package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path ="/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
