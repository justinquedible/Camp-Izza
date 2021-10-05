package com.campizza.backend.campizzabackend.security.payloads;



public class RosterSectionInfoRequest {
    private Boolean personal;
    private Boolean weeks;
    private Boolean attendance;
    private Boolean medicalInfo;
    private Boolean parent;
    private Boolean emergency;
    private Boolean payments;
    private Boolean counselors;

    public RosterSectionInfoRequest() {
    }

    public Boolean getPersonal() {
        return personal;
    }

    public void setPersonal(Boolean personal) {
        this.personal = personal;
    }

    public Boolean getWeeks() {
        return weeks;
    }

    public void setWeeks(Boolean weeks) {
        this.weeks = weeks;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public Boolean getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(Boolean medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public Boolean getPayments() {
        return payments;
    }

    public void setPayments(Boolean payments) {
        this.payments = payments;
    }

    public Boolean getCounselors() {
        return counselors;
    }

    public void setCounselors(Boolean counselors) {
        this.counselors = counselors;
    }
}
