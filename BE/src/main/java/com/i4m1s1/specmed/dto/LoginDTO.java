package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.persistence.MedicalEmployee;

import java.util.List;


public class LoginDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
