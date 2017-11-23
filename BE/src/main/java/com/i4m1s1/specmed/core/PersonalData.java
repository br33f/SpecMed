package com.i4m1s1.specmed.core;

import com.i4m1s1.specmed.core.enums.Gender;

import java.util.Date;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class PersonalData {
    private String pesel;
    private String name;
    private String surname;
    private Gender gender;
    private Date birthday;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
