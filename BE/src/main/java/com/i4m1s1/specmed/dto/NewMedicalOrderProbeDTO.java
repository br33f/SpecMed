package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.MedicalOrder;
import com.i4m1s1.specmed.persistence.Probe;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Deprecated
public class NewMedicalOrderProbeDTO {

    /** {@link com.i4m1s1.specmed.persistence.Probe} data */
    private String probeId;
    private String probePrice;
    private String probeComment;
    private Customer probeCustomer;
    private MedicalEmployee probeMedicalEmployee;

    /** */
    private String id;
    @Dictionary(DictionaryNames.ORDER_TYPE) //PROBES lub MEDICAL_PROCEDURES
    private String type;
    private MedicalEmployee employeeReporter;

    /** Create {@link com.i4m1s1.specmed.persistence.MedicalOrder} with Probe */
    public MedicalOrder<Probe> createMedicalOrder() {
        Probe probe = new Probe();
        probe.setComment(probeComment);
        probe.setCustomer(probeCustomer);
        probe.setId(probeId);
        probe.setMedicalEmployee(probeMedicalEmployee);
        probe.setPrice(probePrice);

        MedicalOrder<Probe> order = new MedicalOrder<>();
        order.setAction(probe);
        order.setEmployeeReporter(employeeReporter);
        order.setId(id);
        order.setType(type);

        return order;
    }


    public String getProbeId() {
        return probeId;
    }

    public void setProbeId(String probeId) {
        this.probeId = probeId;
    }

    public String getProbePrice() {
        return probePrice;
    }

    public void setProbePrice(String probePrice) {
        this.probePrice = probePrice;
    }

    public String getProbeComment() {
        return probeComment;
    }

    public void setProbeComment(String probeComment) {
        this.probeComment = probeComment;
    }

    public Customer getProbeCustomer() {
        return probeCustomer;
    }

    public void setProbeCustomer(Customer probeCustomer) {
        this.probeCustomer = probeCustomer;
    }

    public MedicalEmployee getProbeMedicalEmployee() {
        return probeMedicalEmployee;
    }

    public void setProbeMedicalEmployee(MedicalEmployee probeMedicalEmployee) {
        this.probeMedicalEmployee = probeMedicalEmployee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MedicalEmployee getEmployeeReporter() {
        return employeeReporter;
    }

    public void setEmployeeReporter(MedicalEmployee employeeReporter) {
        this.employeeReporter = employeeReporter;
    }
}
