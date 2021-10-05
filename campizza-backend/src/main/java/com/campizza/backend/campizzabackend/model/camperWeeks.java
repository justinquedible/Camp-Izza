package com.campizza.backend.campizzabackend.model;

public class camperWeeks {

    private String weekID;

    private String startDate;

    private String endDate;

    private String status;

    public camperWeeks() {
    }

    public camperWeeks(String weekID, String startDate, String endDate, String status) {
        this.weekID = weekID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public camperWeeks(Weeks week, String status, int num){
        this.weekID = String.valueOf(num);
        this.startDate = week.getStartDate().toString();
        this.endDate = week.getEndDate().toString();
        this.status = status;
    }

    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
