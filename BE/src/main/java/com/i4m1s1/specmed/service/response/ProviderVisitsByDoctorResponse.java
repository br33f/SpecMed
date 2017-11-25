package com.i4m1s1.specmed.service.response;

import com.i4m1s1.specmed.persistence.Visit;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ProviderVisitsByDoctorResponse implements ServiceResponse{
    private List<Visit> visitList;

    public ProviderVisitsByDoctorResponse(List<Visit> visitList) {
        this.visitList = visitList;
    }

    public List<Visit> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }
}
