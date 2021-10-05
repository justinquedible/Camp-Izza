package com.campizza.backend.campizzabackend.security.payloads;

import java.util.List;

public class SessionEditWeekRequest {

    List allWeeks;

    String cutoff;

    private String amTimeStart; 
    private String amTimeEnd; 
    private String amECTimeStart; 
    private String amECTimeEnd; 
    private String pmTimeStart; 
    private String pmTimeEnd; 
    private String pmECTimeStart; 
    private String pmECTimeEnd; 
    private String fullTimeStart; 
    private String fullTimeEnd; 
    private String fullECTimeStart; 
    private String fullECTimeEnd; 
    private String amSelected;
    private String amECSelected;
    private String pmSelected;
    private String pmECSelected;
    private String fullSelected;
    private String fullECSelected;

    public SessionEditWeekRequest() {
    }

    public List getAllWeeks() {
        return allWeeks;
    }

    public void setAllWeeks(List allWeeks) {
        this.allWeeks = allWeeks;
    }

    public String getCutoff() {
        return cutoff;
    }

    public void setCutoff(String cutoff) {
        this.cutoff = cutoff;
    }

    public String getAmTimeStart() {
        return amTimeStart;
    }

    public void setAmTimeStart(String amTimeStart) {
        this.amTimeStart = amTimeStart;
    }

    public String getAmTimeEnd() {
        return amTimeEnd;
    }

    public void setAmTimeEnd(String amTimeEnd) {
        this.amTimeEnd = amTimeEnd;
    }

    public String getAmECTimeStart() {
        return amECTimeStart;
    }

    public void setAmECTimeStart(String amECTimeStart) {
        this.amECTimeStart = amECTimeStart;
    }

    public String getAmECTimeEnd() {
        return amECTimeEnd;
    }

    public void setAmECTimeEnd(String amECTimeEnd) {
        this.amECTimeEnd = amECTimeEnd;
    }

    public String getPmTimeStart() {
        return pmTimeStart;
    }

    public void setPmTimeStart(String pmTimeStart) {
        this.pmTimeStart = pmTimeStart;
    }

    public String getPmTimeEnd() {
        return pmTimeEnd;
    }

    public void setPmTimeEnd(String pmTimeEnd) {
        this.pmTimeEnd = pmTimeEnd;
    }

    public String getPmECTimeStart() {
        return pmECTimeStart;
    }

    public void setPmECTimeStart(String pmECTimeStart) {
        this.pmECTimeStart = pmECTimeStart;
    }

    public String getPmECTimeEnd() {
        return pmECTimeEnd;
    }

    public void setPmECTimeEnd(String pmECTimeEnd) {
        this.pmECTimeEnd = pmECTimeEnd;
    }

    public String getFullTimeStart() {
        return fullTimeStart;
    }

    public void setFullTimeStart(String fullTimeStart) {
        this.fullTimeStart = fullTimeStart;
    }

    public String getFullTimeEnd() {
        return fullTimeEnd;
    }

    public void setFullTimeEnd(String fullTimeEnd) {
        this.fullTimeEnd = fullTimeEnd;
    }

    public String getFullECTimeStart() {
        return fullECTimeStart;
    }

    public void setFullECTimeStart(String fullECTimeStart) {
        this.fullECTimeStart = fullECTimeStart;
    }

    public String getFullECTimeEnd() {
        return fullECTimeEnd;
    }

    public void setFullECTimeEnd(String fullECTimeEnd) {
        this.fullECTimeEnd = fullECTimeEnd;
    }

    public String getAmSelected() {
        return amSelected;
    }

    public void setAmSelected(String amSelected) {
        this.amSelected = amSelected;
    }

    public String getAmECSelected() {
        return amECSelected;
    }

    public void setAmECSelected(String amECSelected) {
        this.amECSelected = amECSelected;
    }

    public String getPmSelected() {
        return pmSelected;
    }

    public void setPmSelected(String pmSelected) {
        this.pmSelected = pmSelected;
    }

    public String getPmECSelected() {
        return pmECSelected;
    }

    public void setPmECSelected(String pmECSelected) {
        this.pmECSelected = pmECSelected;
    }

    public String getFullSelected() {
        return fullSelected;
    }

    public void setFullSelected(String fullSelected) {
        this.fullSelected = fullSelected;
    }

    public String getFullECSelected() {
        return fullECSelected;
    }

    public void setFullECSelected(String fullECSelected) {
        this.fullECSelected = fullECSelected;
    }
}
