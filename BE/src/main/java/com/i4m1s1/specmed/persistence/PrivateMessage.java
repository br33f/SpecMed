package com.i4m1s1.specmed.persistence;

import org.springframework.data.annotation.Id;

/**
 * Klasa reprezentująca pojedyncza wiadomosc od lub do lekarza.
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class PrivateMessage {

    @Id
    private String id;
    private String content;
    private Customer customer;
    private MedicalEmployee medicalEmployee;
    private boolean isCustomerSender;
    private Long sendTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public MedicalEmployee getMedicalEmployee() {
        return medicalEmployee;
    }

    public void setMedicalEmployee(MedicalEmployee medicalEmployee) {
        this.medicalEmployee = medicalEmployee;
    }

    public boolean isCustomerSender() {
        return isCustomerSender;
    }

    public void setCustomerSender(boolean customerSender) {
        isCustomerSender = customerSender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }
}
