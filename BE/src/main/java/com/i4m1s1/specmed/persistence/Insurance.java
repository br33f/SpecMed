package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Insurance {
    @Id
    private String id;

    // Dane ubezpieczenia
    private String name;
    private Float price;
    private String notes;
    private String insurancePolicyNumber;

    // Powiązania z usługami
    @Dictionary(DictionaryNames.PROBES)
    private List<String> probeList;
    @Dictionary(DictionaryNames.MEDICAL_PROCEDURES)
    private List<String> procedureList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(String insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public List<String> getProbeList() {
        return probeList;
    }

    public void setProbeList(List<String> probeList) {
        this.probeList = probeList;
    }

    public List<String> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<String> procedureList) {
        this.procedureList = procedureList;
    }
}
