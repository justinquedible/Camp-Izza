package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.Camper;


import java.util.*;

public class AttendanceResponse {
    private Date date;


    Map<String, Map<String, Map<String, String>>> camperRecords = new HashMap<>();


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Map<String, Map<String, Map<String, String>>> getCamperRecords() {
        return camperRecords;
    }

    public void setCamperRecords(Map<String, Map<String, Map<String, String>>> camperRecords) {
        this.camperRecords = camperRecords;
    }

    private void initializeGroup(String group) {
        if (!this.camperRecords.containsKey(group)) {
            Map<String, Map<String, String>> camperDict = new HashMap<>();
            this.camperRecords.put(group, camperDict);
        }
    }

    public void addExpectedAMCamperRecord(String group, List<Camper> camperList) {
        initializeGroup(group);
        for (Camper camper : camperList) {
            Map<String, String> camperStatus = new HashMap<>();
            camperStatus.put("firstName", camper.getFirstName());
            camperStatus.put("lastName", camper.getLastName());
            camperStatus.put("expected", "AM");
            camperStatus.put("status", "Absent");

            this.camperRecords.get(group).put(String.valueOf(camper.getId()), camperStatus);
        }
    }

    public void addExpectedPMCamperRecord(String group, List<Camper> camperList) {
        initializeGroup(group);
        for (Camper camper : camperList) {
            Map<String, String> camperStatus = new HashMap<>();

            if (!this.camperRecords.get(group).containsKey(String.valueOf(camper.getId()))) {
                camperStatus.put("firstName", camper.getFirstName());
                camperStatus.put("lastName", camper.getLastName());
                camperStatus.put("expected", "PM");
                camperStatus.put("status", "Absent");
                this.camperRecords.get(group).put(String.valueOf(camper.getId()), camperStatus);
            } else {
                this.camperRecords.get(group).get(String.valueOf(camper.getId())).replace("expected", "Full");
            }
        }
    }

    public void updatePresentCurrentDateAMCamperRecord(String group, List<Camper> camperList) {
        initializeGroup(group);

        for (Camper camper : camperList) {
            Map<String, String> camperStatus = new HashMap<>();
            if (!this.camperRecords.get(group).containsKey(String.valueOf(camper.getId()))) {
                camperStatus.put("firstName", camper.getFirstName());
                camperStatus.put("lastName", camper.getLastName());
                camperStatus.put("expected", "None");
                camperStatus.put("status", "AM");
                this.camperRecords.get(group).put(String.valueOf(camper.getId()), camperStatus);
            } else {
                this.camperRecords.get(group).get(String.valueOf(camper.getId())).replace("status", "AM");
            }
        }
    }

    public void updatePresentCurrentDatePMCamperRecord(String group, List<Camper> camperList) {
        initializeGroup(group);

        for (Camper camper : camperList) {
            Map<String, String> camperStatus = new HashMap<>();
            // System.out.println("PM");
            // System.out.println(camper.getFirstName());
            if (!this.camperRecords.get(group).containsKey(String.valueOf(camper.getId()))) {
                camperStatus.put("firstName", camper.getFirstName());
                camperStatus.put("lastName", camper.getLastName());
                camperStatus.put("expected", "None");
                camperStatus.put("status", "PM");
                this.camperRecords.get(group).put(String.valueOf(camper.getId()), camperStatus);
            } else {
                this.camperRecords.get(group).get(String.valueOf(camper.getId())).replace("status", "AM", "Full");
                this.camperRecords.get(group).get(String.valueOf(camper.getId())).replace("status", "Absent", "PM");
            }
        }
    }


    public void updatePresentCurrentDateBonusCamperRecord(String group, List<String> camperNames, String time) {
        initializeGroup(group);
        for (int i = 0; i < camperNames.size(); i++) {
            Map<String, String> camperStatus = new HashMap<>();
            camperStatus.put("bonusName", camperNames.get(i).split(",")[1]);
            camperStatus.put("status", time);
            this.camperRecords.get(group).put("bonus" + String.valueOf(i), camperStatus);
        }
    }

}
