package com.i4m1s1.specmed.Model;

import org.springframework.data.annotation.Id;

public class Employee {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
