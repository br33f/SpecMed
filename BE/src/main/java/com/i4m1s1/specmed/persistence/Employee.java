package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.PersonalData;
import org.springframework.data.annotation.Id;

/**
 * Encja zawierająca dane pracownika
 */
public class Employee {
    @Id
    private String id;

    private PersonalData personalData;

    public Employee() {
    }

    public Employee(PersonalData personalData) {
        this.personalData = personalData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
