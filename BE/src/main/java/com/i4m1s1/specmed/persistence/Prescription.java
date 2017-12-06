package com.i4m1s1.specmed.persistence;

import org.springframework.data.annotation.Id;

/**
 * Encja zawierająca dane recept
 */
public class Prescription {
    @Id
    public String id;
    public String name;
    public String employeeId;

}
