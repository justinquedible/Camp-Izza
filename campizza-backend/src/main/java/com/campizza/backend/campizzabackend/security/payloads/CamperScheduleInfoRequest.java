package com.campizza.backend.campizzabackend.security.payloads;

public class CamperScheduleInfoRequest {

    private Long camperID;
    private int year;

    public Long getCamperID() {
        return camperID;
    }

    public void setCamperID(Long camperID) {
        this.camperID = camperID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
