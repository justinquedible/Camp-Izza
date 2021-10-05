package com.campizza.backend.campizzabackend.security.payloads;

import java.util.Date;

public class AttendanceRequest {
    private Date date;

    private Long camperID;

    private String time;

    private String bonusCamperName;

    private String bonusCamperGroup;



    public AttendanceRequest(Date date) {
        this.date = date;
    }

    public AttendanceRequest(Date date, Long id, String time) {
        this.date = date;
        this.camperID = id;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public Long getCamperID() {
        return camperID;
    }

    public String getTime() {
        return time;
    }

    public String getBonusCamperName() {
        return bonusCamperName;
    }

    public String getBonusCamperGroup() {
        return bonusCamperGroup;
    }
 }
