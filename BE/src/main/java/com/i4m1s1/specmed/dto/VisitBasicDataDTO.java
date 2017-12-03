package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Visit;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class VisitBasicDataDTO {
    private String id;
    private long date;
    private String medicalEmployeeId;
    private String medicalEmployeeName;
    private String medicalEmployeeSurname;
    private String place;

    public VisitBasicDataDTO(Visit visit) {
        MedicalEmployee medicalEmployee = visit.getMedicalEmpoyee();
        if(medicalEmployee != null) {
            setMedicalEmployeeId(medicalEmployee.getId());
            setMedicalEmployeeName(medicalEmployee.getPersonalData().getName());
            setMedicalEmployeeSurname(medicalEmployee.getPersonalData().getSurname());
        }
        setId(visit.getId());
        setPlace(visit.getPlace());
        setDate(visit.getDate());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getMedicalEmployeeId() {
        return medicalEmployeeId;
    }

    public void setMedicalEmployeeId(String medicalEmployeeId) {
        this.medicalEmployeeId = medicalEmployeeId;
    }

    public String getMedicalEmployeeName() {
        return medicalEmployeeName;
    }

    public void setMedicalEmployeeName(String medicalEmployeeName) {
        this.medicalEmployeeName = medicalEmployeeName;
    }

    public String getMedicalEmployeeSurname() {
        return medicalEmployeeSurname;
    }

    public void setMedicalEmployeeSurname(String medicalEmployeeSurname) {
        this.medicalEmployeeSurname = medicalEmployeeSurname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
