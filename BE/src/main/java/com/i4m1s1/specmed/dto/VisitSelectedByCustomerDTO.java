package com.i4m1s1.specmed.dto;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class VisitSelectedByCustomerDTO {
    private String visitId;
    private String customerId;

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
