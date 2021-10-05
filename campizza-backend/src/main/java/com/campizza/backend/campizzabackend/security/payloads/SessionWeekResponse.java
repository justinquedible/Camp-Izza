package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.Weeks;

import java.util.Date;
import java.util.List;

public class SessionWeekResponse {
    private List<Weeks> allWeeks;
    private Date cutoff;

    public SessionWeekResponse() {
    }

    public SessionWeekResponse(List<Weeks> allWeeks, Date cutoff) {
        this.allWeeks = allWeeks;
        this.cutoff = cutoff;
    }

    public List<Weeks> getAllWeeks() {
        return allWeeks;
    }

    public void setAllWeeks(List<Weeks> allWeeks) {
        this.allWeeks = allWeeks;
    }

    public Date getCutoff() {
        return cutoff;
    }

    public void setCutoff(Date cutoff) {
        this.cutoff = cutoff;
    }
}
