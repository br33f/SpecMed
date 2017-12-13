package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.persistence.Visit;

/**
 * Data transfer object dla wizyty
 * @author br33f
 */

public class VisitDTO {
    private String id;
    private String medicalEmployeeId;
    private String customerId;
    private String price;
    private String status;
    private String place;
    private long dateStart;
    private long dateEnd;

    public VisitDTO() {

    }

    public VisitDTO(Visit visit) {
       setId(visit.getId());
       setCustomerId(visit.getCustomer().getId());
       setMedicalEmployeeId(visit.getMedicalEmployee().getId());
       setDateEnd(visit.getDateEnd());
       setDateStart(visit.getDateStart());
       setPlace(visit.getPlace());
       setPrice(visit.getPrice());
       setStatus(visit.getStatus());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicalEmployeeId() {
        return medicalEmployeeId;
    }

    public void setMedicalEmployeeId(String medicalEmployeeId) {
        this.medicalEmployeeId = medicalEmployeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getDateStart() {
        return dateStart;
    }

    public void setDateStart(long dateStart) {
        this.dateStart = dateStart;
    }

    public long getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(long dateEnd) {
        this.dateEnd = dateEnd;
    }
}
