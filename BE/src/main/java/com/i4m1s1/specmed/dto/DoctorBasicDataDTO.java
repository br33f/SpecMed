package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.persistence.MedicalEmployee;

import java.util.List;

/**
 * Data transfer object dla podstawowych danych pracownika medycznego
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public class DoctorBasicDataDTO {
    private String id;
    private String name;
    private String surname;
    private List<String> specializations;
    private String gender;

    public DoctorBasicDataDTO(MedicalEmployee me) {
        PersonalData pd = me.getPersonalData();
        setName(pd.getName());
        setSurname(pd.getSurname());
        setGender(pd.getGender());
        setSpecializations(me.getSpecializationList());
        setId(me.getId());
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

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
