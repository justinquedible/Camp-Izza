package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.RegisteredWeeks;
import com.campizza.backend.campizzabackend.model.Weeks;
import com.campizza.backend.campizzabackend.model.camperWeeks;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

public class ScheduleInfoResponse {
    private String name;

    private Long userId;

    private int numShirts;


    private int currentYear;

    private int pricingBaseRate;

    private int pricingExtraRate;

    private List<camperWeeks> currentWeeksRegistered;

    public ScheduleInfoResponse() {

    }

    public ScheduleInfoResponse(RegisteredWeeks registeredWeeks){
    }
    // Constructor for weeks
    public ScheduleInfoResponse(RegisteredWeeks registeredWeeks, List<camperWeeks> weeks, int numShirts){
        this.currentYear = registeredWeeks.getCurrentYear();
        this.pricingBaseRate = registeredWeeks.getPricingBaseRate();
        this.pricingExtraRate = registeredWeeks.getPricingExtraRate();
        this.currentWeeksRegistered = weeks;
        this.numShirts = numShirts;


    }


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

    public int getNumShirts() {
        return numShirts;
    }

    public void setNumShirts(int numShirts) {
        this.numShirts = numShirts;
    }

    public int getPricingBaseRate() {
        return pricingBaseRate;
    }

    public void setPricingBaseRate(int pricingBaseRate) {
        this.pricingBaseRate = pricingBaseRate;
    }

    public int getPricingExtraRate() {
        return pricingExtraRate;
    }

    public void setPricingExtraRate(int pricingExtraRate) {
        this.pricingExtraRate = pricingExtraRate;
    }

    public List<camperWeeks> getCurrentWeeksRegistered() {
        return currentWeeksRegistered;
    }

    public void setCurrentWeeksRegistered(List<camperWeeks> currentWeeksRegistered) {
        this.currentWeeksRegistered = currentWeeksRegistered;
    }
}
