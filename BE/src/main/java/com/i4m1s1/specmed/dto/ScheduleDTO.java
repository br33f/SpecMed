package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.Probe;
import com.i4m1s1.specmed.persistence.Procedure;
import com.i4m1s1.specmed.persistence.Visit;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ScheduleDTO {

    private Long startTime;
    private Long endTime;
    private String place;
    private String type;
    private Customer customer;
    private boolean done; //czy juz po zabiegu. Mozna pominac.

    public static ScheduleDTO generate(Procedure p) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setStartTime(p.getDate());
        scheduleDTO.setEndTime(null);
        scheduleDTO.setCustomer(p.getCustomer());
        scheduleDTO.setDone(false); //default - pominąć na FE
        scheduleDTO.setPlace("-");
        scheduleDTO.setType("PROCEDURE");
        return scheduleDTO;
    }

    public static ScheduleDTO generate(Visit v) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDone(false);
        scheduleDTO.setCustomer(v.getCustomer());
        scheduleDTO.setStartTime(v.getDateStart());
        scheduleDTO.setEndTime(v.getDateEnd());
        scheduleDTO.setType("VISIT");
        scheduleDTO.setPlace(v.getPlace());
        return scheduleDTO;
    }

    public static ScheduleDTO generate(Probe p) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setPlace("-");
        scheduleDTO.setType("PROBE");
        scheduleDTO.setCustomer(p.getCustomer());
        scheduleDTO.setDone(false); //pominac na FE
        scheduleDTO.setStartTime(p.getDate());
        scheduleDTO.setEndTime(null);
        return scheduleDTO;
    }
        public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}
