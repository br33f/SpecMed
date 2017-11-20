package com.i4m1s1.specmed.persistence;

import org.springframework.data.annotation.Id;

public class Unit {
    @Id
    public String id;

    public Employee[] employees;
}
