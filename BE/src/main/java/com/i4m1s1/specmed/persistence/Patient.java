package com.i4m1s1.specmed.persistence;

import org.springframework.data.annotation.Id;

public class Patient {
    @Id
    private String name;
    private String surname;
    private String address;
    private int age;
}
