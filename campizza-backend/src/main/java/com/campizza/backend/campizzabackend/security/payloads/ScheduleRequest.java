package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.Weeks;
import com.campizza.backend.campizzabackend.model.camperWeeks;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRequest {

    private String name;

    private Long userId;

    private int currentYear;

    private List<camperWeeks> currentWeeksRegistered;

    private int numShirts;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public List<camperWeeks> getCurrentWeeksRegistered() {
        return currentWeeksRegistered;
    }

    public void setCurrentWeeksRegistered(List<camperWeeks> currentWeeksRegistered) {
        this.currentWeeksRegistered = currentWeeksRegistered;
    }

    public int getNumShirts() {
        return numShirts;
    }

    public void setNumShirts(int numShirts) {
        this.numShirts = numShirts;
    }
}
