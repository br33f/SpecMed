package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;

import java.util.List;

public class SimpleCustomerDataDTO {
    private String id;
    private String name;
    private String surname;
    private String pesel;

    public SimpleCustomerDataDTO(Customer customer) {
        PersonalData pd = customer.getPersonalData();
        setName(pd.getName());
        setSurname(pd.getSurname());
        setPesel(pd.getPesel());
        setId(customer.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
