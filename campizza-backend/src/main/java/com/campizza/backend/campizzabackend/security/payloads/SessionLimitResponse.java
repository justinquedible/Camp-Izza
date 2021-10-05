package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.RegisteredWeeks;

public class SessionLimitResponse {

    int datesAMLimit;

    int datesPMLimit;

    int cocoAMLimit;

    int cocoPMLimit;

    int treeAMLimit;

    int treePMLimit;

    int leadAMLimit;

    int leadPMLimit;

    public SessionLimitResponse() {

    }

    public SessionLimitResponse(RegisteredWeeks program) {
        this.cocoAMLimit = program.getCocoAMLimit();
        this.cocoPMLimit = program.getCocoPMLimit();
        this.datesAMLimit = program.getDateAMLimit();
        this.datesPMLimit = program.getDatePMLimit();
        this.treeAMLimit = program.getTreeAMLimit();
        this.treePMLimit = program.getTreePMLimit();
        this.leadAMLimit = program.getLeadAMLimit();
        this.leadPMLimit = program.getLeadPMLimit();
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
