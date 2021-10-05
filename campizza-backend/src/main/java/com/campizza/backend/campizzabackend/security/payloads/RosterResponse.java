package com.campizza.backend.campizzabackend.security.payloads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RosterResponse {
    public Map<Long, Map<String, String>> campers = new HashMap<>();

    private Map<Long, Map<String, String>> temp;

    public void addCamper(Long camperID) {
        Map<String, String> camperDict = new HashMap<>();
        this.campers.put(camperID, camperDict);
    }

    public void addAttribute(Long camperID, String attributeKey, String attributeValue) {
        if (!this.campers.containsKey(camperID)) {
            this.addCamper(camperID);
        }
        this.campers.get(camperID).put(attributeKey, attributeValue);
    }

    public void addCamperList(List<Long> camperList) {
        for (Long camperID : camperList) {
            addCamper(camperID);
        }
    }

    public void addAttributesList(List<Long> camperList, String attributeKey, String attributeValue) {
        for (Long camperID : camperList) {
            addAttribute(camperID, attributeKey, attributeValue);
        }
    }
}
