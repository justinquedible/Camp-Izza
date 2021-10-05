package com.campizza.backend.campizzabackend.security.payloads;

public class CamperInfoRequest {
    private String firstName;
    private Long userID;
    private Long camperID;
    private Float amount;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public void setCamperID(Long cid) {
        this.camperID = cid;
    }
    public Long getCamperID() {
        return camperID;
    }
}