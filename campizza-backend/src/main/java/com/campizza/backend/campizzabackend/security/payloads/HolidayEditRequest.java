package com.campizza.backend.campizzabackend.security.payloads;

public class HolidayEditRequest {
    private String holiday;
    private Long holidayID;

    public HolidayEditRequest() {
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public Long getHolidayID() {
        return holidayID;
    }

    public void setHolidayID(Long holidayID) {
        this.holidayID = holidayID;
    }
}
