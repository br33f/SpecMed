package com.i4m1s1.specmed.dto;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class VisitSelectedByCustomerDTO {
    private String visitId;
    private String cusomerId;

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getCusomerId() {
        return cusomerId;
    }

    public void setCusomerId(String cusomerId) {
        this.cusomerId = cusomerId;
    }
}
