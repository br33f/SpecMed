package com.i4m1s1.specmed.core;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class PrescriptionRow {
    private String medicineName; //nazwa leku
    private String dose; //dawka np. w mg

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}
