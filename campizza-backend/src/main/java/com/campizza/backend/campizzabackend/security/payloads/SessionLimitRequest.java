package com.campizza.backend.campizzabackend.security.payloads;

public class SessionLimitRequest {
    int year;

    int datesAMLimit;

    int datesPMLimit;

    int cocoAMLimit;

    int cocoPMLimit;

    int treeAMLimit;

    int treePMLimit;

    int leadAMLimit;

    int leadPMLimit;

    public SessionLimitRequest() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDatesAMLimit() {
        return datesAMLimit;
    }

    public void setDatesAMLimit(int datesAMLimit) {
        this.datesAMLimit = datesAMLimit;
    }

    public int getDatesPMLimit() {
        return datesPMLimit;
    }

    public void setDatesPMLimit(int datesPMLimit) {
        this.datesPMLimit = datesPMLimit;
    }

    public int getCocoAMLimit() {
        return cocoAMLimit;
    }

    public void setCocoAMLimit(int cocoAMLimit) {
        this.cocoAMLimit = cocoAMLimit;
    }

    public int getCocoPMLimit() {
        return cocoPMLimit;
    }

    public void setCocoPMLimit(int cocoPMLimit) {
        this.cocoPMLimit = cocoPMLimit;
    }

    public int getTreeAMLimit() {
        return treeAMLimit;
    }

    public void setTreeAMLimit(int treeAMLimit) {
        this.treeAMLimit = treeAMLimit;
    }

    public int getTreePMLimit() {
        return treePMLimit;
    }

    public void setTreePMLimit(int treePMLimit) {
        this.treePMLimit = treePMLimit;
    }

    public int getLeadAMLimit() {
        return leadAMLimit;
    }

    public void setLeadAMLimit(int leadAMLimit) {
        this.leadAMLimit = leadAMLimit;
    }

    public int getLeadPMLimit() {
        return leadPMLimit;
    }

    public void setLeadPMLimit(int leadPMLimit) {
        this.leadPMLimit = leadPMLimit;
    }
}
